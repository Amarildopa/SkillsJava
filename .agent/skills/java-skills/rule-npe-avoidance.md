# Guardrail: NPE Avoidance & Null Safety

## Context
NullPointerExceptions (NPEs) are one of the most common issues in Java. Modern Java provides several patterns to minimize null checks and make the code more robust and readable.

## Guardrails

### 1. Collections and Arrays
- **RULE**: NEVER return `null` for a method that should return a collection or an array.
- **PRACTICE**: Return `Collections.emptyList()`, `List.of()`, `Set.of()`, or an empty array.
- **REASON**: Prevents the caller from needing an explicit null check before iterating or checking size.

### 2. Defensive Programming with `Objects`
- **RULE**: Use `Objects.requireNonNull(object, "Error message")` for mandatory parameters.
- **PRACTICE**: Fail-fast at the beginning of a method or constructor.
- **REASON**: Provides clear error messages and prevents the null from propagating deep into the system.

### 3. Using `Optional`
- **RULE**: Use `Optional<T>` as a return type to indicate that a value might be absent.
- **PRACTICE**: 
    - Use `Optional.ofNullable(obj).ifPresent(action)` for clean flow.
    - Avoid using `Optional` for method parameters or class fields (expensive and not its intended use).
- **DEBATE**: For simple "if not null then do", a standard `if (obj != null)` is often more performant and readable. Use `Optional` when chaining operations or specifically designed for API returns.

### 4. Database Persistence
- Always initialize collection fields in Entities/Records to avoid nulls after instantiation.

## Reference Demo
See implementation in: `fundamentals-npe-avoidance/`

---
**Author:** Wanderlei Souza
