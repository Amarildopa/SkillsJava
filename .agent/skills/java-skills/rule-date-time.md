# Guardrail: Date and Time API (JSR-310)

## Context
Legacy Java date classes (`java.util.Date`, `Calendar`) are mutable, not thread-safe, and have confusing APIs (e.g., months starting at 0). Modern Java (8+) provides the `java.time` package which is immutable and thread-safe.

## Guardrails

### 1. Avoid Legacy Classes
- **NEVER** use `java.util.Date`, `java.util.Calendar`, or `java.text.SimpleDateFormat`.

### 2. Persistence and Auditing
- **RULE**: Use `java.time.Instant` for all timestamps saved to databases or logs. 
- **REASON**: Standardizes everything to UTC, avoiding timezone-related bugs during data exchange between systems.

### 3. Presentation Layer
- **RULE**: Use `java.time.ZonedDateTime` only when displaying dates to the user or when a specific timezone is a business requirement (e.g., flight schedules).
- **CONVERSION**: Always convert from `Instant` to `ZonedDateTime` at the edges of the system (UI/API output).

### 4. Local Context Only
- **RULE**: Use `java.time.LocalDateTime`, `LocalDate`, or `LocalTime` only when time zones are truly irrelevant (e.g., "The store opens at 08:00" regardless of where the store is).
- **WARNING**: Do not use `LocalDateTime` for global events as it loses the timezone offset.

### 5. Thread Safety
- All `java.time` objects are immutable. Use them freely in multi-threaded environments without synchronization.

## Reference Demo
See implementation in: `fundamentos-datetime/`

---
**Author:** Wanderlei Souza
