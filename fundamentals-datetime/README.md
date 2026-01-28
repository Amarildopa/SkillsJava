# Skill: Date and Time API

This mini-project focuses on replacing the use of legacy `java.util.Date` and `java.util.Calendar` with modern APIs from the `java.time` package.

## Key Learnings:

1.  **Instant**: The temporal "anchor". Should be used for database persistence, audit timestamps, and logs. It is always in UTC.
2.  **ZonedDateTime**: The "human" representation of an `Instant`. Should be used only in the display layer or when the time zone is an explicit business rule (e.g., flight schedules).
3.  **LocalDateTime**: Avoid for global persistence. Useful only for relative schedules (e.g., "every day at 08:00") where the timezone doesn't matter.
4.  **Immutability**: Unlike `Date`, `java.time` classes are immutable and thread-safe.

## How to run:
As it is a standard Maven project:
```bash
mvn compile exec:java -Dexec.mainClass="com.skillsjava.datetime.DateTimeDemo"
```
