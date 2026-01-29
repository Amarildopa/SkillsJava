# Guardrail: Constants without Hacks

## Context
Defining constants is a daily task in programming, but it's often done incorrectly. Common mistakes include using "magic numbers" and abusing interfaces to store constants (the Constant Interface Antipattern).

## Guardrails

### 1. Avoid Magic Numbers
- **RULE**: NEVER use numbers or strings directly in logic without a descriptive name.
- **PRACTICE**: Use `private static final` fields for internal constants or `public static final` for shared ones.
- **REASON**: Magic numbers are hard to read, maintain, and understand.

### 2. No Constant Interfaces
- **RULE**: NEVER create an interface solely to store constants.
- **PRACTICE**: Interfaces should define behavior (contracts), not data.
- **REASON**: It pollutes the domain model and creates long-term binary compatibility issues.

### 3. Dedicated Constant Utility Classes
- **RULE**: Use a `final` class with a `private` constructor for grouping constants.
- **PRACTICE**:
    ```java
    public final class AppConstants {
        public static final int MAX_RETRY_ATTEMPTS = 3;
        private AppConstants() { /* Prevent instantiation */ }
    }
    ```
- **REASON**: Prevents inheritance and instantiation of a class that only holds data.

### 4. Use Enums for Discrete Sets
- **RULE**: Use `enum` for sets of related, named constants.
- **PRACTICE**: `enum Status { ACTIVE, INACTIVE, ARCHIVED }`
- **REASON**: Enums provide type safety, are self-documenting, and can contain logic.

### 5. Naming Convention
- **RULE**: Use `SCREAMING_SNAKE_CASE` for `static final` constants.
- **REASON**: Standard Java convention for readability and distinguishing constants from variables.

## Reference Demo
See implementation in: `fundamentals-constants/`

---
**Author:** Wanderlei Souza
