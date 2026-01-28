package com.skillsjava.clock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

/**
 * Demo: Monotonic Clock vs Wall Clock.
 * Highlights why System.nanoTime() is the only choice for measuring duration.
 */
public final class MonotonicClockDemo {

    private static final Logger logger = LoggerFactory.getLogger(MonotonicClockDemo.class);

    public static void main(String[] args) throws InterruptedException {
        logger.info("=== Monotonic Clock Demo (Pill #40) ===\n");

        demonstrateWallClockUsage();
        demonstrateMonotonicClockUsage();

        logger.info("\n--- Key Differences ---");
        logger.info(
                "1. Wall Clock (currentTimeMillis): Tracks calendar time (UTC). Vulnerable to system clock shifts (NTP).");
        logger.info(
                "2. Monotonic Clock (nanoTime): Tracks duration since an arbitrary point. Jitter-free and always advances.");
    }

    /**
     * ❌ WRONG: Using Wall Clock for duration.
     */
    private static void demonstrateWallClockUsage() throws InterruptedException {
        logger.info("--- Using Wall Clock (System.currentTimeMillis()) ---");

        long start = System.currentTimeMillis();
        Instant timestamp = Instant.ofEpochMilli(start);
        logger.info("Start Timestamp: {}", timestamp);

        // Simulate some work
        performWork();

        long end = System.currentTimeMillis();
        long duration = end - start;

        logger.info("Execution Time (Wall Clock): {} ms", duration);
        logger.warn(
                "Warning: If the system clock synchronized via NTP during execution, this value could be wrong or even negative!\n");
    }

    /**
     * ✅ RIGHT: Using Monotonic Clock for duration.
     */
    private static void demonstrateMonotonicClockUsage() throws InterruptedException {
        logger.info("--- Using Monotonic Clock (System.nanoTime()) ---");

        long startNanos = System.nanoTime();

        // Simulate some work
        performWork();

        long endNanos = System.nanoTime();
        long durationNanos = endNanos - startNanos;

        // Convert to human-readable format
        double durationMillis = durationNanos / 1_000_000.0;
        long durationSeconds = TimeUnit.NANOSECONDS.toSeconds(durationNanos);

        logger.info("Execution Time (Monotonic): {} ms ({} seconds)", durationMillis, durationSeconds);
        logger.info("Note: This measurement is independent of system clock changes and has nanosecond precision.");
    }

    private static void performWork() throws InterruptedException {
        // Simulating a task that takes around 500ms
        Thread.sleep(500);
    }
}
