# Guardrail: Encapsulation & Testability

## Context
Encapsulation is a core pillar of OO design. Increasing visibility creates coupling and increases maintenance costs. A common conflict arises when private logic is complex and needs testing.

## Guardrails

### 1. Principle of Least Visibility
- **RULE**: Keep classes and attributes as private as possible.
- **PRACTICE**: Instance variables should almost always be `private`.
- **REASON**: Prevents external manipulation, protects invariants, and allows internal evolution without breaking clients.

### 2. Test Observable Behavior
- **RULE**: Priority should be testing public services, not internal implementation details.
- **PRACTICE**: If a private method is wrong, it should manifest as a bug in the public API.
- **REASON**: Testing private methods directly makes tests brittle to refactoring.

### 3. Handling Complex Private Logic (Trade-offs)
- **RULE**: NEVER make a method `public` just for testing.
- **OPTIONS (in order of preference)**:
    1. **Extract Class**: Move the dense logic to a new cohese class where it becomes part of that class's public (or package-private) API.
    2. **Package-Private (Default)**: Relax visibility from `private` to `default` to allow access from a test class in the same package.
    3. **@VisibleForTesting**: Use this annotation (from Guava or similar) to signal that the increased visibility is strictly for tests.

### 4. Design Pressure
- **RULE**: If something is hard to test, it is often a sign of poor design/observability.
- **PRACTICE**: Ask "why is this hard to observe?" instead of "how do I force access to this private method?".

## Reference Demo
See implementation in: `fundamentals-encapsulation-testing/`
