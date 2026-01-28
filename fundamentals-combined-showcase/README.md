# Skill: Combined Best Practices (Java 21)

This project integrates the three main fundamentals learned so far into a single coherent example.

## Integrated Skills:

1.  **NPE Avoidance**:
    - Use of `Optional<T>` to avoid returning `null`.
    - Returning `Collections.emptyList()` instead of `null` for list-returning methods.
    - `Objects.requireNonNull()` in constructors for early failure (Record validation).

2.  **Date and Time API**:
    - Use of `Instant` for precise point-in-time storage (UTC).
    - Conversion to `ZonedDateTime` only at the presentation layer using `ZoneId.systemDefault()`.

3.  **Collections API**:
    - Use of `SequencedSet` (`LinkedHashSet`) to maintain insertion order while ensuring uniqueness.
    - `addFirst()`, `getLast()` and `reversed()` methods from the Java 21 uniform API.
    - Returning `Collections.unmodifiableSequencedCollection` for encapsulation.

4.  **Modern Java (Java 16+)**:
    - Use of `record` for immutable data carriers.

## How to run:
```bash
mvn compile exec:java -Dexec.mainClass="com.skillsjava.showcase.CombinedShowcaseDemo"
```
