# Guardrail: Inheritance Hazards & Final Classes

## Context
Inheritance is one of the most misused features of OO. It breaks encapsulation because a subclass depends on the implementation details of its superclass. Designing for inheritance is difficult and requires explicit documentation and safe-guards.

## Guardrails

### 1. "Design for Inheritance or Prohibit it"
- **RULE**: If a class is not explicitly designed to be extended, mark it as `final`.
- **PRACTICE**: Use `final` by default. Only allow inheritance if you have a clear, documented reason and have followed safe-guards.
- **REASON**: Prevents fragile base class problems and unexpected behavior in subclasses.

### 2. No Overridable Methods in Constructors
- **RULE**: NEVER call a method in a constructor that can be overridden by a subclass.
- **PRACTICE**: If you need to perform initialization logic, use `private` or `final` methods.
- **REASON**: The superclass constructor runs BEFORE the subclass constructor. If the overridable method is called, it might access subclass fields that haven't been initialized yet, leading to `NullPointerException`.

### 3. Document Self-Use
- **RULE**: If an overridable method calls another overridable method internally, this MUST be documented (using `@implSpec`).
- **PRACTICE**: Be aware that overriding one method might affect the behavior of another if they are linked internally (e.g., `addBatch` calling `addItem`).
- **REASON**: Prevents side effects where logic (like discounts or logging) is applied multiple times or skipped entirely.

### 4. Use Sealed Classes (Java 17+)
- **RULE**: Use `sealed` classes to provide a controlled middle ground between `final` and wide-open public inheritance.
- **PRACTICE**: `public sealed class Shape permits Circle, Square {}`
- **REASON**: Provides compiler-level protection, ensuring only allowed classes can extend yours.

### 5. Favor Composition over Inheritance
- **RULE**: Use composition, decorators, or wrappers instead of inheritance when possible.
- **REASON**: Composition is more flexible, easier to test, and doesn't break encapsulation.

## Reference Demo
See implementation in: `fundamentals-inheritance-hazards/`

---
**Author:** Wanderlei Souza
