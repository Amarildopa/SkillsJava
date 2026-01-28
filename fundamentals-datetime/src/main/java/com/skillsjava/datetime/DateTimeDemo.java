package com.skillsjava.datetime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Skill: Java Fundamentals - Date and Time API (JSR-310).
 * Refactored to follow all best practices (final, logging).
 */
public final class DateTimeDemo {

    private static final Logger logger = LoggerFactory.getLogger(DateTimeDemo.class);
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss z");

    public static void main(String[] args) {
        logger.info("=== Java Date & Time API Demo ===\n");

        // 1. The Source of Truth: Instant
        // Represents a single point on the timeline in UTC.
        // Best for persistence (DB) and audit logs.
        Instant globalNow = Instant.now();
        logger.info("1. Instant (UTC): {}", globalNow);

        // 2. User Presentation: ZonedDateTime
        // Converts the global Instant into a specific regional context.
        ZonedDateTime saoPauloTime = globalNow.atZone(ZoneId.of("America/Sao_Paulo"));
        ZonedDateTime tokyoTime = globalNow.atZone(ZoneId.of("Asia/Tokyo"));

        logger.info("2. São Paulo: {}", saoPauloTime.format(FORMATTER));
        logger.info("   Tokyo:     {}", tokyoTime.format(FORMATTER));

        // 3. The LocalDateTime Risk
        // It has no time zone info. It's just a "wall time".
        LocalDateTime wallTime = LocalDateTime.now();
        logger.info("\n3. LocalDateTime (Warning): {}", wallTime);
        logger.warn("   Note: If saved to a DB, the timezone context is lost!");

        // 4. Practical Example: Auditing
        logger.info("\n--- Audit Scenario ---");
        recordEvent("User Login");
    }

    private static void recordEvent(String action) {
        // ALWAYS use Instant to capture WHEN something happened.
        Instant timestamp = Instant.now();

        logger.info("Event: {}", action);
        logger.info("Persisting to DB (UTC): {}", timestamp);

        // For local display/logging only:
        ZonedDateTime local = timestamp.atZone(ZoneId.systemDefault());
        logger.info("Displaying for Admin ({}): {}", ZoneId.systemDefault(), local);
    }
}
