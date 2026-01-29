# Guardrail: Advanced Date & Time (ZonedDateTime vs. Period vs. Duration)

## Context
Handling dates across different time zones is a frequent source of bugs. Relying on `LocalDateTime` for cross-timezone calculations is dangerous because it lacks the absolute point in time (Instant). Additionally, choosing between `Period` and `Duration` is critical for accuracy.

## Guardrails

### 1. Cross-Time Zone Calculations
- **RULE**: NEVER use `LocalDateTime` to calculate intervals between different time zones.
- **PRACTICE**: Use `ZonedDateTime` or `Instant`.
- **REASON**: `ZonedDateTime` represents a specific instant on the universal timeline. Converting between zones using `withZoneSameInstant()` keeps the underlying point in time the same, allowing for accurate physical time difference calculations.

### 2. Physical Time (`Duration`) vs. Calendar Time (`Period`)
- **RULE**: Use `Duration` for measuring technical intervals (nanoseconds, seconds, hours).
- **RULE**: Use `Period` for business logic involving calendar units (days, months, years) where the physical length of a day (23h or 25h due to DST) doesn't matter.
- **PRACTICE**: When calculating "total days" for a long interval, be careful with `Period.getDays()`. It only returns the "day component" (e.g., for 1 month and 30 days, it returns 30, not 60). Use `ChronoUnit.DAYS.between()` or `Duration.toDays()` if physical time is needed.

### 3. Absolute Comparison
- **PRACTICE**: Use `isBefore()`, `isAfter()`, and `isEqual()` on `ZonedDateTime` or `Instant` for semantic comparisons.
- **REASON**: These methods handle the underlying instant accurately, even if the objects represent different time zones or offsets.

## Comparison Table

| Feature | `LocalDateTime` | `ZonedDateTime` | `Instant` |
| :--- | :--- | :--- | :--- |
| **Time Zone Info** | None | Yes | UTC (Implicit) |
| **Represents Instant?** | No | Yes | Yes |
| **DST Aware?** | No | Yes | No (Always UTC) |
| **Primary Use** | Local time (e.g., store opening) | Global events with zone context | System events, audit, DB |

## Reference Demo
See implementation in: `fundamentals-advanced-datetime/`

---
**Author:** Wanderlei Souza
