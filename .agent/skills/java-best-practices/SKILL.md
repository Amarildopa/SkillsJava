---
name: java-best-practices
description: Applies industry-standard Java best practices (Naming, SOLID, Design Patterns, Immutability, Exceptions, Modern Java 17-21+, and Performance).
---

# Java Best Practices Guardrails

## 🎯 Objective
Ensure all Java code generated or refactored follows high-level industry standards, prioritizing readability, maintainability, and security. This skill integrates classic principles (SOLID, Clean Code) with modern features (Java 17, 21+).

## 📋 Core Guardrails

### 1. Naming Conventions & Expressiveness
- **Classes**: `PascalCase` (e.g., `PaymentProcessor`). Use nouns, avoid generic suffixes like `Manager` or `Info`.
- **Methods**: `camelCase` (e.g., `calculateTotal`). Use verbs.
- **Constants**: `UPPER_SNAKE_CASE` (e.g., `MAX_RETRY_COUNT`).
- **Interfaces**: Prefer descriptive names. If it describes an ability, use suffix `Able` (e.g., `Serializable`). Avoid `I` prefix unless strictly required by project conventions.
- **Booleans**: Start with `is`, `has`, `can`, `should` (e.g., `isActive`, `hasPermission`).
- **MEANINGFUL NAMES**: Avoid abbreviations and generic names like `list1`, `data`, `temp`.

### 2. Clean Coding & Readability
- **Guard Clauses**: Prefer "Fail Fast" by returning or throwing early. Avoid deep nesting (`if/else` chains).
    - ✅ **GOOD**: `if (user == null) return; // logic continue`
    - ❌ **BAD**: `if (user != null) { if (active) { ... } }`
- **Method Length**: Aim for methods under 20-30 lines. If it's longer, you probably have more than one responsibility.
- **Boolean Expressions**: Extract complex logic into well-named boolean variables or helper methods.

### 3. SOLID Principles & Architecture
- **Single Responsibility (SRP)**: One class, one reason to change. Separate domain, logic, and infrastructure (Repository pattern).
- **Open-Closed (OCP)**: Use interfaces and abstraction to extend behavior without modifying existing code.
- **Liskov Substitution (LSP)**: Subclasses must be substitutable for their base classes. Avoid breaking parent contracts.
- **Interface Segregation (ISP)**: Better multiple specific interfaces than one giant generic one. (See `rule-service-interfaces.md`).
- **Dependency Inversion (DIP)**: Inject dependencies via constructors. Never use `new` for services inside classes.

### 3. Modern Java Mastery (17 - 21+)
- **Records**: Use for DTOs, Value Objects, and simple data holders. They are immutable by default.
- **Sealed Classes**: Use for controlled hierarchies (e.g., `sealed interface Payment permits Pix, Card`).
- **Pattern Matching**: Use `switch` statements/expressions with type patterns for cleaner logic.
- **Sequenced Collections**: Use `.getFirst()`, `.getLast()`, and `.reversed()` for List/Deque/Set.
- **Virtual Threads (Java 21)**: Use `Executors.newVirtualThreadPerTaskExecutor()` for blocking I/O tasks. Do not pool virtual threads.
- **Structured Concurrency**: Use `StructuredTaskScope` (preview in 21+) to manage subtasks as a single unit or follow the "Owner-Task" relationship.
- **CompletableFuture**: Use for asynchronous non-blocking pipelines. Always specify a custom Executor to avoid blocking the common `ForkJoinPool`.
- **Avoid Shared Mutable State**: Prefer immutable objects and message passing (or `ThreadLocal` safely) over `synchronized` blocks where possible.

### 5. Immutability & Persistence
- **Final by Default**: Mark classes and fields as `final` unless mutation is explicitly required.
- **Defensive Copies**: Return unmodifiable views of collections (`List.copyOf`, `Collections.unmodifiableList`).
- **Entities vs DTOs**: Keep Entities in persistence layers and expose Immutable Records (DTOs) to external layers.

### 5. Robust Exception Handling
- **Specific Exceptions**: Avoid `catch (Exception)`. Catch targeted types (e.g., `IOException`).
- **Never Swallow**: Always log (with stack trace) or rethrow. Use custom exceptions for domain-specific errors.
- **Try-With-Resources**: Mandatory for handling any `AutoCloseable` resources (Files, DB connections).
- **Fail Fast**: Validate parameters at method entry using `Objects.requireNonNull`, `Assert`, or `@Valid`.
- **Global Error Handling**: In web apps, use a centralized controller advice/exception handler to avoid leaking internal stack traces to the user.

### 7. Functional & Stream API
- **Declarative Style**: Use Streams (`filter`, `map`, `flatMap`, `reduce`) for data transformations.
- **Avoid parallelStream()**: Never use `parallelStream()` unless you have benchmarked it for huge datasets. For small lists, it's slower due to overhead.
- **Side Effects**: Streams should be pure; don't modify external state inside a `forEach`.
- **Optional**: Use as a return type only. Never use `Optional.get()` without `isPresent()`. Prefer `.orElseThrow()`.

### 8. Security & Data Integrity
- **SQL Injection**: Always use `PreparedStatements` or JPA/Hibernate parameterized queries. Never concatenate strings in SQL.
- **Sensitive Data**: 
    - Never store passwords or tokens in plain `String` (due to String interning/immutability in heap). Use `char[]` and clear it after use, or use specialized secure containers.
    - Sanitize all external inputs (XSS/Injection protection).
- **Object Serialization**: Avoid Java's native serialization if possible; use JSON/Protobuf for external communication.

### 9. Performance & Resource Management
- **BigDecimal Strategy**: Use `new BigDecimal("0.1")` (String constructor) or `BigDecimal.valueOf(0.1)`. Never use `new BigDecimal(0.1)` (Double constructor) as it introduces precision errors.
- **Primitive Streams**: Use `IntStream`, `LongStream`, `DoubleStream` to avoid autoboxing overhead in heavy calculations.
- **StringBuilder**: Use for string assembly specifically inside loops. Outside loops, modern Java compiler optimizes `+` to `StringBuilder` automatically.

---

## 🔍 Validation Checklist
- [ ] Is naming consistent and expressive?
- [ ] Are dependencies injected via constructor?
- [ ] Are resources handled via try-with-resources?
- [ ] Any "Magic Numbers" that should be constants?
- [ ] Any "XServiceImpl" that could be a direct class?
- [ ] Used `record` for data carriers?
- [ ] Checked if `final` should be added to classes/fields?
- [ ] Interface Segregation Principle followed?

---

**Version**: 2.0 (Refactored & English)
**Updated**: January 2026
**Maintainer**: Google Antigravity
