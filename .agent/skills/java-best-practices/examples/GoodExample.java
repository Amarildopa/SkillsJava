package com.empresa.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Exemplo de BOAS PRÁTICAS em Java
 * Segue os padrões da skill java-best-practices
 */

// 1. MODELO: responsabilidade única, imutável
public final class User {
    private final String id;
    private final String name;
    private final String email;
    private final boolean active;
    
    public User(String id, String name, String email, boolean active) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.active = active;
    }
    
    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public boolean isActive() { return active; }
}

// 2. REPOSITORY: apenas persistência
public interface IUserRepository {
    Optional<User> findById(String id);
    List<User> findAll();
    void save(User user);
    void delete(String id);
}

// 3. IMPLEMENTAÇÃO: com logging apropriado
public class UserRepository implements IUserRepository {
    private static final Logger logger = LoggerFactory.getLogger(UserRepository.class);
    private final Database database;
    
    // Dependency Injection via construtor
    public UserRepository(Database database) {
        this.database = database;
    }
    
    @Override
    public Optional<User> findById(String id) {
        logger.debug("Buscando usuário: {}", id);
        try {
            User user = database.query(id);
            return Optional.ofNullable(user);
        } catch (DatabaseException e) {
            logger.error("Erro ao buscar usuário: {}", id, e);
            throw new UserRepositoryException("Falha ao buscar usuário", e);
        }
    }
    
    @Override
    public List<User> findAll() {
        logger.debug("Buscando todos os usuários");
        try {
            return database.queryAll();
        } catch (DatabaseException e) {
            logger.error("Erro ao buscar usuários", e);
            throw new UserRepositoryException("Falha ao buscar usuários", e);
        }
    }
    
    @Override
    public void save(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User não pode ser null");
        }
        
        logger.info("Salvando usuário: {}", user.getId());
        try {
            database.insert(user);
            logger.debug("Usuário salvo com sucesso: {}", user.getId());
        } catch (DatabaseException e) {
            logger.error("Erro ao salvar usuário: {}", user.getId(), e);
            throw new UserRepositoryException("Falha ao salvar usuário", e);
        }
    }
    
    @Override
    public void delete(String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("User ID não pode ser vazio");
        }
        
        logger.info("Deletando usuário: {}", id);
        try {
            database.delete(id);
            logger.debug("Usuário deletado: {}", id);
        } catch (DatabaseException e) {
            logger.error("Erro ao deletar usuário: {}", id, e);
            throw new UserRepositoryException("Falha ao deletar usuário", e);
        }
    }
}

// 4. SERVICE: lógica de negócio, usa builder
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private static final int MAX_RETRY_ATTEMPTS = 3;
    
    private final IUserRepository repository;
    private final EmailService emailService;
    
    // Dependency Injection
    public UserService(IUserRepository repository, EmailService emailService) {
        this.repository = repository;
        this.emailService = emailService;
    }
    
    public void createUser(String name, String email) {
        // Validação de entrada
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Email inválido");
        }
        
        logger.info("Criando novo usuário: {}", email);
        
        try {
            // Usa Builder Pattern para criar User com múltiplos parâmetros
            User newUser = new UserBuilder()
                .withId(generateId())
                .withName(name)
                .withEmail(email)
                .build();
            
            repository.save(newUser);
            emailService.sendWelcomeEmail(newUser.getEmail());
            
            logger.info("Usuário criado com sucesso: {}", newUser.getId());
        } catch (EmailException e) {
            logger.warn("Email de boas-vindas não enviado: {}", email, e);
            // continua mesmo se email falhar
        } catch (Exception e) {
            logger.error("Erro ao criar usuário: {}", email, e);
            throw new UserServiceException("Falha ao criar usuário", e);
        }
    }
    
    public Optional<User> getUserById(String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("User ID não pode ser vazio");
        }
        
        logger.debug("Recuperando usuário: {}", id);
        return repository.findById(id);
    }
    
    public List<String> getActiveUserEmails() {
        logger.debug("Recuperando emails de usuários ativos");
        
        // Usa Streams em vez de loops manuais
        return repository.findAll()
            .stream()
            .filter(User::isActive)
            .map(User::getEmail)
            .sorted()
            .collect(Collectors.toList());
    }
    
    private String generateId() {
        return "USER_" + System.currentTimeMillis();
    }
}

// 5. BUILDER: para construção complexa
public class UserBuilder {
    private String id;
    private String name;
    private String email;
    private boolean active = true;
    
    public UserBuilder withId(String id) {
        this.id = id;
        return this;
    }
    
    public UserBuilder withName(String name) {
        this.name = name;
        return this;
    }
    
    public UserBuilder withEmail(String email) {
        this.email = email;
        return this;
    }
    
    public UserBuilder inactive() {
        this.active = false;
        return this;
    }
    
    public User build() {
        if (id == null || name == null || email == null) {
            throw new IllegalArgumentException("Campos obrigatórios não preenchidos");
        }
        return new User(id, name, email, active);
    }
}

// 6. EXCEÇÕES CUSTOMIZADAS
public class UserServiceException extends RuntimeException {
    public UserServiceException(String message) {
        super(message);
    }
    
    public UserServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}

public class UserRepositoryException extends RuntimeException {
    public UserRepositoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
