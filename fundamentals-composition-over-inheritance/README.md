# Skill: Java Fundamentals - Composition over Inheritance (Pill #39)

## Objective
Understand why extending concrete classes to add behavior (audit, validation, logging) is dangerous and how the **Decorator Pattern** provides a safer alternative.

## The Fragility of Inheritance
When you use `extends` to intercept a method like `save()`, you depend on the internal implementation of the superclass. If the superclass adds a new method like `saveAll()` that doesn't call `save()`, your intercepted logic (validation) will be **bypassed silently**.

## The Decorator Solution
By implementing the same interface and wrapping the original service (**Composition**), you:
1.  **Protect Invariants**: New interface methods force you to implement them in the decorator.
2.  **Preserve Encapsulation**: You only depend on the public contract, not internal calls of the superclass.
3.  **Lose Nothing**: You still delegate the heavy lifting to the original implementation.

## How to use
```java
// Instead of
class MyValidatedService extends BaseService { ... }

// Use
class MyValidatedDecorator implements ServiceInterface {
    private final ServiceInterface delegate;
    // ... implement all methods ...
}
```

## Running the Demo
```bash
mvn compile exec:java
```
