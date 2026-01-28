# Skill: Java Fundamentals - Monotonic Clock (Pill #40)

## Objective
Learn the difference between the "Wall Clock" (system time) and the "Monotonic Clock" (elapsed time) and why it matters for latency measurement.

## Why use `System.nanoTime()`?
Using `System.currentTimeMillis()` or `Instant.now()` to measure duration is a common mistake. These methods track the **Wall Clock**, which is:
1.  **Mutable**: Can be updated by NTP sync or manual adjustments.
2.  **Not strictly increasing**: It can jump backwards (e.g., during a leap second or daylight saving adjustment).
3.  **Low Precision**: On some OS, it only updates every 10-15ms.

**`System.nanoTime()`** is a **Monotonic Clock**:
1.  **Immutable**: It is not affected by system clock shifts.
2.  **Always advances**: Perfect for measuring elapsed time.
3.  **High Precision**: Nanosecond resolution.

## How to use
```java
long start = System.nanoTime();
// ... code to measure ...
long duration = System.nanoTime() - start;
double millis = duration / 1_000_000.0;
```

## Running the Demo
```bash
mvn compile exec:java
```
