# Guardrail: Template Method in Interfaces

## Context
Implementing interfaces often leads to repetitive code. While Java 8+ introduced `default methods`, they have limitations (no state, cannot override `equals`/`hashCode`). A robust pattern is combining interfaces with `default methods` and `abstract classes` to implement the Template Method pattern.

## Guardrails

### 1. Define Minimal Contracts
- **RULE**: Define a small set of abstract methods (primitive operations) in the interface from which other behaviors can be derived.
- **PRACTICE**: Identify the "core" logic that must be implemented by subclasses.

### 2. Leverage `default methods`
- **RULE**: Use `default methods` for behaviors that can be derived purely from the interface's own methods without needing state.
- **PRACTICE**: Use them for utility-like operations or algorithms that don't depend on instance variables.
- **REASON**: Reduces boilerplate for implementers without forcing inheritance.

### 3. Use Abstract Service Classes (The Skeleton)
- **RULE**: Create an `Abstract[InterfaceName]` class to handle shared state and complex rules that `default methods` cannot express.
- **PRACTICE**:
    - Manage common fields (protected/private state).
    - Implement methods that require state or complex invariants.
- **REASON**: Centralizes common behavior while preserving the interface as the formal contract.

### 4. Implementation Choice
- **RULE**: Allow implementers to choose between implementing the raw interface or extending the abstract class.
- **REASON**: Favor composition and flexibility. Don't force inheritance unless the shared state is absolutely mandatory for the contract to function.

### 5. Proper Overrides
- **RULE**: When extending an abstract class, ensure you override methods that might throw `UnsupportedOperationException` if the base implementation is just a placeholder.

## Reference Demo
See implementation in: `fundamentals-interface-template-method/`

---
**Author:** Wanderlei Souza
