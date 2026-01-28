# ✅ Validation Checklist - Java Best Practices

Use this checklist during code reviews with the `java-best-practices` skill.

---

## 📋 Section 1: Naming & Expressiveness
- [ ] **Classes**: Use `PascalCase`? (e.g., `UserService`, `PaymentProcessor`)
- [ ] **Methods**: Use `camelCase`? (e.g., `calculateTotal()`)
- [ ] **Constants**: Use `UPPER_SNAKE_CASE`? (e.g., `MAX_RETRY_COUNT`)
- [ ] **Booleans**: Start with `is`, `has`, `can`, `should`?
- [ ] **Packages**: All lowercase separated by dots? (e.g., `com.company.payment`)
- [ ] **Meaningful Names**: No abbreviations or generic names (`list1`, `data`)?

---

## 🎯 Section 2: Clean Code & SOLID
- [ ] **Guard Clauses**: Returns/Throws early to avoid nested `if` statements?
- [ ] **Method Length**: Methods focused and under 30 lines?
- [ ] **Boolean Clarity**: Complex conditions extracted to named variables/methods?
- [ ] **Single Purpose**: Does each class have one unique reason to change?
- [ ] **Loose Coupling**: Are services decoupled from infrastructure?
- [ ] **Interface Segregation**: Are interfaces small and focused?

---

## 🔒 Section 3: Imutabilidade & Data Handling
- [ ] **Immutable Records**: Are DTOs/Value Objects implemented as `record`?
- [ ] **Final by Default**: Are fields `private final` unless mutation is required?
- [ ] **No Public Fields**: Is encapsulation preserved?
- [ ] **Defensive Copies**: Are collections returned as unmodifiable views?

---

## ⚠️ Section 4: Exception Handling & Resources
- [ ] **Specific Catch**: No generic `catch (Exception e)`?
- [ ] **No printStackTrace**: Use a Logger!
- [ ] **Try-with-Resources**: Used for closing all `AutoCloseable` resources?
- [ ] **Cause Preservation**: Is the original exception passed to custom exceptions?
- [ ] **Global Handling**: Internal stack traces prevented from leaking to UI?

---

## 🎨 Section 5: Modern Java & Concurrency (17 - 21+)
- [ ] **Records**: Used for data-only carriers?
- [ ] **Sealed Hierarchies**: Used for domain models with fixed variants?
- [ ] **Pattern Matching**: Used in `switch` expressions for cleaner type checking?
- [ ] **Virtual Threads**: Used correctly for I/O tasks? (no pooling, Task-Per-Thread)
- [ ] **Structured Concurrency**: Used for coordination of subtasks?
- [ ] **CompletableFuture**: Used with custom executors?

---

## 🏗️ Section 6: Dependency Injection & API Design
- [ ] **Constructor Injection**: All dependencies passed through the constructor?
- [ ] **No 'new' for Services**: Services don't instantiate their colleagues?
- [ ] **Fail-Fast Validation**: Parameters validated at the start of methods?
- [ ] **Optional as Return**: Used for finders instead of returning `null`?
- [ ] **No Optional.get()**: Using `.orElseThrow()` or `.map()` safely?

---

## 🎨 Section 7: Streams & Performance
- [ ] **Declarative Streams**: No side-effects inside streams (no external state mutation)?
- [ ] **No parallelStream()**: Unless large datasets justify the overhead?
- [ ] **BigDecimal Safety**: String constructor or `valueOf` used (never `new BigDecimal(double)`)?
- [ ] **Primitive Streams**: Used `IntStream/LongStream` for high-performance math?
- [ ] **StringBuilder**: Used ONLY inside loops for string assembly?
- [ ] **Magic Numbers**: All literals replaced by naming-appropriate constants?

---

## 🛡️ Section 8: Security & Integrity
- [ ] **SQL Injection Protection**: PreparedStatements/JPA used? No string concat in SQL?
- [ ] **Sensitive Data Safety**: Passwords/Tokens NOT stored in `String`?
- [ ] **Input Sanitization**: All external/user inputs cleaned or validated?
- [ ] **Least Visibility**: Classes/Members private by default?
- [ ] **Native Serialization**: Avoided in favor of JSON/Protobuf for external APIs?

---

## 🚀 Final Review Checklist
- [ ] Section 1: Naming & Expressiveness ✅
- [ ] Section 2: SOLID & SRP ✅
- [ ] Section 3: Immutability ✅
- [ ] Section 4: Exceptions & Resources ✅
- [ ] Section 5: Modern Java Features ✅
- [ ] Section 6: DI & API Design ✅
- [ ] Section 7: Streams & Performance ✅
- [ ] Section 8: Security & Testing ✅

---

**Version**: 2.0 (English Refactor)  
**Last Update**: January 2026
