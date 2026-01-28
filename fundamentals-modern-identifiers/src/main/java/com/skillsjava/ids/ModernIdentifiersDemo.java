package com.skillsjava.ids;

import com.github.f4b6a3.uuid.UuidCreator;
import com.github.f4b6a3.ulid.UlidCreator;
import com.github.f4b6a3.ulid.Ulid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;
import java.util.List;
import java.util.ArrayList;

/**
 * Demo: Modern Identifiers (UUIDv4 vs UUIDv7 vs ULID).
 * Demonstrates the generation of different types of identifiers
 * and explains why time-ordered IDs are better for databases.
 * Refactored to follow all best practices (final, logging).
 */
public final class ModernIdentifiersDemo {

    private static final Logger logger = LoggerFactory.getLogger(ModernIdentifiersDemo.class);

    public static void main(String[] args) {
        logger.info("=== Modern Identifiers Demo ===\n");

        // 1. UUID v4 (Random) - The Legacy Choice
        logger.info("--- UUID v4 (Random) ---");
        for (int i = 0; i < 3; i++) {
            logger.info("v4: {}", UUID.randomUUID());
        }
        logger.warn("Note: These are completely random. High entropy is bad for B-trees.\n");

        // 2. UUID v7 (Time-Ordered) - The Modern Choice
        logger.info("--- UUID v7 (Time-Ordered Epoch) ---");
        for (int i = 0; i < 3; i++) {
            UUID v7 = UuidCreator.getTimeOrderedEpoch();
            logger.info("v7: {} (Timestamp embedded)", v7);
        }
        logger.info("Note: These are sequential. Great for DB index locality.\n");

        // 3. ULID (Universally Unique Lexicographically Sortable Identifier)
        logger.info("--- ULID (Monotonic) ---");
        for (int i = 0; i < 3; i++) {
            Ulid ulid = UlidCreator.getMonotonicUlid();
            logger.info("ULID: {} (26 chars, Base32)", ulid);
        }
        logger.info("Note: Sortable, URL-friendly, and very readable.\n");

        // Comparison of sorting behavior
        logger.info("--- Sorting behavior (Simulated timeline) ---");
        List<UUID> v7List = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            v7List.add(UuidCreator.getTimeOrderedEpoch());
        }

        logger.info("Generated v7 IDs (Insertion order):");
        v7List.forEach(id -> logger.info("  {}", id));

        logger.info("Sorted v7 IDs:");
        v7List.stream().sorted().forEach(id -> logger.info("  {}", id));
        logger.info("Notice: They are naturally ordered by creation time!");
    }
}
