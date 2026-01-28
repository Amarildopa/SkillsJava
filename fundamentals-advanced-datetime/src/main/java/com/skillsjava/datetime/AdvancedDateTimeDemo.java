package com.skillsjava.datetime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Demo: Advanced Date & Time (Pill #38).
 * Helping John McClane calculate the time until Christmas in Los Angeles,
 * starting from New York.
 * Focuses on ZonedDateTime, Instant, and Duration for cross-timezone accuracy.
 */
public final class AdvancedDateTimeDemo {

    private static final Logger logger = LoggerFactory.getLogger(AdvancedDateTimeDemo.class);
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss VV");

    public static void main(String[] args) {
        logger.info("=== Advanced Date & Time Demo (Pill #38) ===\n");

        ZoneId newYork = ZoneId.of("America/New_York");
        ZoneId losAngeles = ZoneId.of("America/Los_Angeles");

        // 1. Capture the exact Instant (Universal timeline)
        Instant nowInstant = Instant.now();

        // 2. Interpret the instant in specific contexts
        ZonedDateTime nowNY = nowInstant.atZone(newYork);
        ZonedDateTime nowLA = nowInstant.atZone(losAngeles);

        // 3. Define the target (Christmas in LA)
        // Christmas starts at midnight (00:00:00) on Dec 25th in LA zone.
        ZonedDateTime christmasLA = ZonedDateTime.of(
                nowLA.getYear(), 12, 25, 0, 0, 0, 0, losAngeles);

        // Business Logic: If Christmas has already passed in LA this year, target next
        // year.
        if (!nowLA.isBefore(christmasLA)) {
            logger.info("Christmas in LA has already passed (or is today)! Calculating for next year...");
            christmasLA = christmasLA.plusYears(1);
        }

        // 4. Calculate the Duration (Physical time in nanoseconds/seconds)
        // We use Instant to calculate the physical duration between two points in the
        // universe.
        Duration countdown = Duration.between(nowInstant, christmasLA.toInstant());

        // Display the results
        logger.info("John McClane's Current Status:");
        logger.info("  Current Time (New York):    {}", nowNY.format(FORMATTER));
        logger.info("  Current Time (Los Angeles): {}", nowLA.format(FORMATTER));
        logger.info("  Target Christmas (LA):      {}", christmasLA.format(FORMATTER));

        logger.info("\nTime remaining until Christmas (Yippee-ki-yay!):");
        logger.info("  {} Days, {} Hours, {} Minutes, and {} Seconds",
                countdown.toDays(),
                countdown.toHoursPart(),
                countdown.toMinutesPart(),
                countdown.toSecondsPart());

        logger.info("\n--- Key Lessons ---");
        logger.info(
                "1. Duration vs Period: Use Duration for physical time (seconds/nanos). Period is for human calendar units.");
        logger.info("2. DST Safety: Duration accurately counts seconds even across DST shifts (23h or 25h days).");
        logger.info(
                "3. Timeline Safety: Comparing ZonedDateTimes via isBefore() effectively compares their underlying Instants.");
    }
}
