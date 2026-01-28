# Guardrail: Monotonic Clock vs. Wall Clock

## Context
Measuring execution time seems simple, but using the wrong clock can lead to subtle bugs, especially in distributed systems or performance-critical applications. The primary issue is using "Calendar Time" (Wall Clock) for measuring durations.

## Guardrails

### 1. Elapsed Time & Benchmarking
- **RULE**: NEVER use `System.currentTimeMillis()` or `Instant.now()` to measure the duration of a code block.
- **PRACTICE**: Use `System.nanoTime()`.
- **REASON**: `currentTimeMillis` represents the "Wall Clock" (Unix Epoch). It can jitter, jump backwards or forwards due to NTP synchronization, leap seconds, or manual system clock adjustments. `System.nanoTime()` provides a monotonic clock that always advances, regardless of system time changes.

### 2. Timestamps and Events
- **RULE**: Use `System.currentTimeMillis()`, `Instant.now()`, or `ZonedDateTime` for audit logs, database timestamps, and event markers.
- **REASON**: These values represent a specific point in human history (Calendar Time), which is what you need for logs and persistence.

### 3. Precision vs. Accuracy
- **PRACTICE**: Use `System.nanoTime()` for high-precision needs.
- **REASON**: `currentTimeMillis` precision is often limited to 10-15ms on some operating systems (like older Windows versions). `nanoTime` provides nanosecond precision (though not necessarily nanosecond accuracy, it's significantly better).

## Table: Which one to use?

| Use Case | Recommended Method | Why? |
| :--- | :--- | :--- |
| **Measuring Latency** | `System.nanoTime()` | Monotonic, immune to clock drift. |
| **Benchmarking** | `System.nanoTime()` | High precision, always advances. |
| **Audit Logs** | `Instant.now()` | Represents a specific date/time. |
| **DB CreatedAt** | `Instant.now()` | Standard for persistence. |
| **Timeout Calculations** | `System.nanoTime()` | Prevent negative durations. |

## Code Example

### ❌ WRONG (Calendar Time)
```java
long start = System.currentTimeMillis();
doWork();
long duration = System.currentTimeMillis() - start; // Can be negative!
```

### ✅ RIGHT (Monotonic Time)
```java
long startNanos = System.nanoTime();
doWork();
long durationNanos = System.nanoTime() - startNanos;
double durationMillis = durationNanos / 1_000_000.0;
```

## Reference Demo
See implementation in: `fundamentals-monotonic-clock/`
