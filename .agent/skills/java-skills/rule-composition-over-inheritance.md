# Guardrail: Composition over Inheritance (Decorator Pattern)

## Context
Inheritance is often used to "intercept" behavior (like adding validation or logging) by extending a concrete class. This creates a high dependency on the superclass's internal implementation, leading to "fragile base class" bugs where new base methods bypass subclass logic.

## Guardrails

### 1. Behavior Interception
- **RULE**: NEVER extend a concrete class just to add cross-cutting concerns (validation, auditing, logging).
- **PRACTICE**: Use the **Decorator Pattern**. Create a class that implements the same interface, holds a private instance of the original service, and delegates calls.
- **REASON**: If the base class adds a new method (e.g., `saveAll()`), a subclass implementation might be bypassed. A decorator implementation forces you to handle all interface methods explicitly.

### 2. Sharing Logic
- **RULE**: Avoid `BaseController`, `AbstractService`, or similar inheritance-based common logic holders.
- **PRACTICE**: Inject dedicated utility services via constructor (Composition).
- **REASON**: Inheritance creates hidden dependencies and shared mutable state, making the code harder to test and evolve.

### 3. Cross-Cutting Concerns (Spring/AOP)
- **PRACTICE**: For audit or widespread logging, prefer AOP (`@Aspect`) or Interceptors over inheritance.

### 4. When is Inheritance OK?
- **RULE**: Use inheritance ONLY when there is a genuine "is-a" relationship AND the superclass was designed for extension (e.g., `Payment` -> `PixPayment`).
- **PRACTICE**: Prefer `sealed` classes to control who can extend your logic.

## Table: Comparison

| Feature | Inheritance (`extends`) | Composition (Decorator) |
| :--- | :--- | :--- |
| **Coupling** | High (Internal implementation dependency) | Low (Interface contract dependency) |
| **Integrity** | Fragile (Base changes can bypass logic) | Robust (Compiler-checked contracts) |
| **Flexibility** | Static (Fixed at compile time) | Dynamic (Can wrap any implementation) |
| **Encapsulation** | Violated (Subclass knows super internals) | Preserved (Only knows public API) |

## Code Example

### ❌ FRAGILE (Inheritance)
```java
class ValidatedOrderService extends OrderService {
    @Override
    public void save(Order order) {
        validate(order);
        super.save(order);
    }
    // BUG: If OrderService adds 'saveAll()', validation is bypassed!
}
```

### ✅ ROBUST (Composition/Decorator)
```java
class ValidatedOrderService implements OrderService {
    private final OrderService delegate;

    public ValidatedOrderService(OrderService delegate) {
        this.delegate = delegate;
    }

    @Override
    public void save(Order order) {
        validate(order);
        delegate.save(order);
    }

    @Override
    public void saveAll(List<Order> orders) {
        orders.forEach(this::validate);
        delegate.saveAll(orders);
    }
}
```

## Reference Demo
See implementation in: `fundamentals-composition-over-inheritance/`
