# Guardrail: Useful Interfaces vs. XServiceImpl Antipattern

## Context
Interfaces are contracts defining behavior, not architectural bureaucracy. A common mistake is creating interfaces for every service by default (the `XServiceImpl` pattern), which adds unnecessary complexity without real benefit.

## Guardrails

### 1. Avoid `XServiceImpl`
- **RULE**: DO NOT create an interface if there is only one implementation. Use a direct class instead.
- **PRACTICE**: If you eventually need a second implementation, refactor to an interface then.
- **REASON**: Reduces boilerplate and "meaningless" class names. Modern IDEs make refactoring to an interface trivial when actually needed.

### 2. Meaningful Naming for Defaults
- **RULE**: If an interface is truly needed and has a primary/standard implementation, prefer the suffix `Default` (e.g., `UserServiceDefault`) instead of `Impl`.
- **REASON**: `Default` conveys the intent of being the standard behavior, whereas `Impl` is just a technical suffix.

### 3. Interface Segregation Principle (ISP)
- **RULE**: Keep interfaces small and cohesive.
- **PRACTICE**: If an interface has dozens of methods, split it. An implementation should not be forced to define methods it doesn't need.
- **REASON**: High cohesion and low coupling.

### 4. Responsibilities, not just Interfaces
- **RULE**: Do not use an interface to mask a "God Class" design.
- **PRACTICE**: Instead of one giant `OrderService`, split into `ShippingCalculator`, `StockValidator`, and `InvoiceIssuer`.
- **REASON**: Each component should have a single reason to change (SRP).

### 5. Caution with `default methods`
- **RULE**: Be careful when adding `default methods` to existing, widely used interfaces.
- **REASON**: They are "injected" into all existing implementations, which might violate internal invariants or thread-safety assumptions of those classes.

## Reference Demo
See implementation in: `fundamentals-service-interfaces/`

---
**Author:** Wanderlei Souza
