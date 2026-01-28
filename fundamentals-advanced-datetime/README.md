# Skill: Java Fundamentals - Advanced Date & Time (Pill #38)

## Objective
Avoid "Wall Clock" assumptions when dealing with multiple time zones. Learn how to use `ZonedDateTime`, `Instant`, and `Duration` for robust time calculations.

## The John McClane Scenario
John McClane is in New York and needs to reach Los Angeles before Christmas. Calculating the "time left" requires:
1.  **ZonedDateTime**: To represent the current time in NY and LA contexts.
2.  **Instant**: To represent the shared point in the universe for calculations.
3.  **Duration**: To measure the physical time (seconds) remaining, avoiding the pitfalls of `Period.getDays()`.

## Why not `Period`?
- `Period` measures calendar time (e.g., "30 days" might be 720 hours or 721 hours depending on DST).
- `Period.getDays()` only returns the day component of a period (e.g., if the period is "1 month and 30 days", `getDays()` returns 30, not 60).
- **`Duration`** measures physical time and is DST-safe for technical intervals.

## How to use
```java
Instant now = Instant.now();
ZonedDateTime target = ZonedDateTime.of(2025, 12, 25, 0, 0, 0, 0, ZoneId.of("America/Los_Angeles"));
Duration diff = Duration.between(now, target.toInstant());
System.out.println("Remaining days: " + diff.toDays());
```

## Running the Demo
```bash
mvn compile exec:java
```
