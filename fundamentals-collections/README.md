# Skill: Collections API

This mini-project demonstrates modern practices for the Java Collections Framework, specifically highlighting features introduced in Java 9 (`List.of`) and Java 21 (Sequenced Collections).

## Key Concepts:

1.  **Immutability by Default**: Using `List.of()`, `Set.of()`, and `Map.of()` to create read-only collections that are safer and more performant.
2.  **Sequenced Collections (Java 21)**: Utilizing the new `SequencedCollection` interface to handle order consistently across List, Set, and Deque.
3.  **Predictable Access**: Using `getFirst()` and `getLast()` instead of index-based or iterator-based access when only the boundaries matter.
4.  **Reversed Views**: How to obtain a reversed view of a collection without copying data.

## How to run:
```bash
mvn compile exec:java -Dexec.mainClass="com.skillsjava.collections.CollectionsDemo"
```
