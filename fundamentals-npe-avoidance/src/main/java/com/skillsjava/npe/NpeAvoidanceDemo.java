package com.skillsjava.npe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Skill: Java Fundamentals - NPE Avoidance.
 * Refactored to follow all best practices (final, logging).
 */
public final class NpeAvoidanceDemo {

    private static final Logger logger = LoggerFactory.getLogger(NpeAvoidanceDemo.class);

    public static void main(String[] args) {
        logger.info("=== NPE Avoidance Demo ===\n");

        // 1. Returning empty collections instead of null
        List<String> users = findUsers("search_query");
        logger.info("1. Collection safety: User count = {}", users.size());

        // 2. Defensive check with Objects
        try {
            registerUser(null);
        } catch (NullPointerException e) {
            logger.error("2. Defensive check caught expected error: {}", e.getMessage());
        }

        // 3. Optional vs standard null check
        String user = "Antigravity";

        logger.info("\n3a. Using Optional:");
        Optional.ofNullable(user).ifPresent(NpeAvoidanceDemo::sendEmail);

        logger.info("3b. Using standard null check:");
        if (user != null) {
            sendEmail(user);
        }
    }

    /**
     * RULE: Never return null for collections.
     */
    private static List<String> findUsers(String query) {
        boolean found = false;
        if (!found) {
            return Collections.emptyList(); // Far better than: return null;
        }
        return List.of("Admin", "User1");
    }

    /**
     * RULE: Fail-fast with clear messages.
     */
    private static void registerUser(String username) {
        Objects.requireNonNull(username, "Username cannot be null for registration");
        logger.info("Registering: {}", username);
    }

    private static void sendEmail(String name) {
        logger.info("Sending email to: {}", name);
    }
}
