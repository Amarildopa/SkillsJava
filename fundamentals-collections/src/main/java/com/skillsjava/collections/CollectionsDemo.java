package com.skillsjava.collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.*;

/**
 * Demo for Modern Java Collections (Java 9 to 21).
 * Refactored to follow all best practices (final, logging).
 */
public final class CollectionsDemo {

    private static final Logger logger = LoggerFactory.getLogger(CollectionsDemo.class);

    public static void main(String[] args) {
        logger.info("=== Modern Java Collections Demo ===\n");
        demonstrateImmutability();
        demonstrateSequencedCollections();
    }

    /**
     * Shows how to create immutable collections (Java 9+).
     */
    private static void demonstrateImmutability() {
        logger.info("--- Immutability (Java 9+) ---");

        // List.of creates an immutable List. No nulls allowed.
        List<String> immutableList = List.of("Java", "Spring", "Maven");
        logger.info("Immutable List: {}", immutableList);

        try {
            immutableList.add("New Element");
        } catch (UnsupportedOperationException e) {
            logger.warn("Caught expected exception: Cannot modify immutable list.");
        }

        // Map.of creates an immutable Map
        Map<String, Integer> immutableMap = Map.of("Twenty-One", 21, "Seventeen", 17);
        logger.info("Immutable Map: {}", immutableMap);
    }

    /**
     * Shows Java 21 Sequenced Collections.
     */
    private static void demonstrateSequencedCollections() {
        logger.info("\n--- Sequenced Collections (Java 21) ---");

        // ArrayList implementation of SequencedCollection
        SequencedCollection<String> list = new ArrayList<>();
        list.add("Middle");
        list.addFirst("Start");
        list.addLast("End");

        logger.info("Full list: {}", list);
        logger.info("First element: {}", list.getFirst());
        logger.info("Last element: {}", list.getLast());

        // Reversed view (does NOT copy the list)
        SequencedCollection<String> reversed = list.reversed();
        logger.info("Reversed view: {}", reversed);

        // SequencedSet with LinkedHashSet
        SequencedSet<String> set = new LinkedHashSet<>(List.of("One", "Two", "Three"));
        logger.info("Sequenced Set (LinkedHashSet): {}", set);
        logger.info("Set First: {}", set.getFirst());

        // Move to first (Specific to SequencedSet implementation)
        set.addFirst("Zero");
        logger.info("Set after addFirst: {}", set);
    }
}
