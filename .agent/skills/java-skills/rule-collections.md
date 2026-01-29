# Rule: Collections API (Modern Java)

Guidelines for utilizing the Java Collections Framework focusing on safety, immutability, and Java 21+ features.

## 1. Favor Immutability
Always use immutable collections when the data should not change. This prevents side effects and is thread-safe.

- **❌ WRONG (Legacy/Verbose):**
  ```java
  List<String> list = new ArrayList<>();
  list.add("A");
  list = Collections.unmodifiableList(list);
  ```

- **✅ RIGHT (Modern):**
  ```java
  List<String> list = List.of("A", "B", "C"); // Immutable
  Set<String> set = Set.of("A", "B"); // Immutable
  Map<String, Integer> map = Map.of("K1", 1, "K2", 2); // Immutable
  ```

## 2. Sequenced Collections (Java 21+)
Use the new uniform API for collections with a defined order.

- **Methods**: `getFirst()`, `getLast()`, `addFirst()`, `addLast()`, `reversed()`.
- **Interfaces**: `SequencedCollection`, `SequencedSet`, `SequencedMap`.

```java
SequencedCollection<String> list = new ArrayList<>(List.of("A", "B"));
list.addFirst("First");
list.addLast("Last");
String first = list.getFirst();
String last = list.getLast();
```

## 3. Avoid Arrays if possible
Prefer `List` over raw `Arrays` for better API support and type safety, unless performance in a tight loop is critical.

## 4. Specific Implementations
- Use `ArrayList` for random access (default choice).
- Use `LinkedList` ONLY if you have heavy insertions/deletions at the ends AND you are using it as a `Deque`.
- Use `HashSet` for distinct elements where order doesn't matter.
- Use `LinkedHashSet` (SequencedSet) when you need uniqueness AND insertion order.
- Use `HashMap` for key-value pairs.
- Use `TreeMap` (SequencedMap) for sorted keys.

---
**Author:** Wanderlei Souza
