package com.skillsjava.userservice;

import com.github.f4b6a3.uuid.UuidCreator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * SHOWCASE: High-Quality UserService.
 * Integrates all knowledge pills:
 * - UUIDv7 for IDs
 * - Records for Immutability
 * - Guard Clauses (Fail Fast)
 * - Constructor Injection (DI)
 * - Optional for safe returns
 * - char[] for sensitive data (Security)
 * - Proper Logging & Custom Exceptions
 */
public class UserServiceDemo {
    public static void main(String[] args) {
        System.out.println("=== Best Practices UserService Demo ===\n");

        UserRepository repository = new InMemoryUserRepository();
        UserService userService = new UserService(repository);

        try {
            // 1. Sign Up
            System.out.println("1. Registering user...");
            char[] password = "securePassword123".toCharArray();
            User user = userService.signUp("Alice Smith", "alice@example.com", password);
            System.out.println("User registered: " + user.name() + " with ID: " + user.id());

            // 2. Login
            System.out.println("\n2. Attempting login...");
            userService.login("alice@example.com", "securePassword123".toCharArray());
            System.out.println("Login successful!");

            // 3. Find User
            System.out.println("\n3. Finding user by ID...");
            userService.findById(user.id())
                    .ifPresent(u -> System.out.println("Found: " + u.name() + " (" + u.email() + ")"));

        } catch (UserServiceException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}

/**
 * DOMAIN: Using Record for immutability and modern syntax.
 */
record User(UUID id, String name, String email, char[] passwordHash) {
    // Note: In real app, passwordHash would be a salt+hash string/byte[].
    // Here we use char[] to demonstrate sensitive data handling.
}

/**
 * CONTRACT: Repository interface for decoupling.
 */
interface UserRepository {
    void save(User user);

    Optional<User> findByEmail(String email);

    Optional<User> findById(UUID id);
}

/**
 * SERVICE: The core business logic.
 */
final class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository repository;

    // Dependency Injection via Constructor
    public UserService(UserRepository repository) {
        this.repository = Objects.requireNonNull(repository, "Repository cannot be null");
    }

    /**
     * Registers a new user.
     * Uses Guard Clauses for Fail-Fast validation.
     */
    public User signUp(String name, String email, char[] password) {
        if (name == null || name.isBlank())
            throw new UserServiceException("Name is required");
        if (email == null || !email.contains("@"))
            throw new UserServiceException("Invalid email");
        if (password == null || password.length < 8)
            throw new UserServiceException("Password too short");

        if (repository.findByEmail(email).isPresent()) {
            throw new UserServiceException("User already exists with email: " + email);
        }

        logger.info("Creating new user account for: {}", email);

        // UUIDv7: Best for B-tree DB indexes (Sequential)
        UUID userId = UuidCreator.getTimeOrderedEpoch();

        // In a real scenario, we would hash the password here.
        // We'll just "simulate" a hash.
        char[] passwordHash = Arrays.copyOf(password, password.length);

        User user = new User(userId, name, email, passwordHash);
        repository.save(user);

        // Security: Clear the sensitive data from memory as soon as possible
        Arrays.fill(password, ' ');

        return user;
    }

    /**
     * Authenticates a user.
     */
    public void login(String email, char[] password) {
        logger.debug("Login attempt for email: {}", email);

        User user = repository.findByEmail(email)
                .orElseThrow(() -> new UserServiceException("Invalid credentials"));

        // Compare password (In reality, compare the hash)
        if (!Arrays.equals(user.passwordHash(), password)) {
            logger.warn("Failed login attempt for email: {}", email);
            throw new UserServiceException("Invalid credentials");
        }

        // Security: Burn the password cleartext
        Arrays.fill(password, ' ');
    }

    public Optional<User> findById(UUID id) {
        return repository.findById(id);
    }
}

/**
 * ERROR HANDLING: Specific domain exception.
 */
class UserServiceException extends RuntimeException {
    public UserServiceException(String message) {
        super(message);
    }
}

/**
 * INFRASTRUCTURE: Simple implementation for demo.
 */
class InMemoryUserRepository implements UserRepository {
    private final Map<UUID, User> usersById = new HashMap<>();
    private final Map<String, User> usersByEmail = new HashMap<>();

    @Override
    public void save(User user) {
        usersById.put(user.id(), user);
        usersByEmail.put(user.email(), user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(usersByEmail.get(email));
    }

    @Override
    public Optional<User> findById(UUID id) {
        return Optional.ofNullable(usersById.get(id));
    }
}
