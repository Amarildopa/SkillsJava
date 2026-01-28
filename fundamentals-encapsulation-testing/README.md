# Skill: Encapsulation & Testability

This project explores the balance between keeping implementation details hidden (Encapsulation) and making code easy to verify (Testability).

## The Dificulty of Testing Private Methods
If a method is `private`, it means it's an internal detail. Direct tests against it make your test suite brittle—if you rename or refactor that inner method, your tests break even if the public behavior is identical.

## The Solution: Improving Design
Instead of using Reflection or making everything `public` (which pollutes your API), we follow these strategies:

### 1. Extract Logic
If a private method is too complex, it probably belongs to its own class. By moving it to a separate component (like `BaseDiscountPolicy` in this demo), we can test that component's public API without exposing the `DiscountService` internals.

### 2. Relax Visibility (Package-Private)
Java's default visibility (no modifier) allows access to any class in the same package. This is ideal for tests, as your test source (usually in `src/test/java`) should mirror the package structure of your main source.

### 3. @VisibleForTesting
A documentation-only annotation that warns other developers: "This method has higher visibility than it should purely for the sake of unit testing. Do not call it from other parts of the production code."

## How to Run
```bash
mvn compile exec:java
```
