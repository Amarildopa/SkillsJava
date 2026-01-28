# Skill: Template Method in Interfaces

This project demonstrates the powerful combination of modern Java Interfaces and Abstract Classes to implement a clean Template Method pattern.

## The Strategy

### 1. Interface as the Contract
The interface (`TaxCalculator`) define the "What". 
- It has a "Primitive Operation": `calculateItem(BigDecimal base)`.
- It defines a "Template Method" using a `default method`: `calculateOrder(List<BigDecimal> items)`. This algorithm is derived purely from the primitive operation.

### 2. Abstract Class as the Skeleton
The abstract class (`AbstractTaxCalculator`) handles the "How" for shared state.
- It manages the `rate` field (State).
- It provides a base implementation of the primitive operation (`calculateItem`) using that state.

### 3. Implementations
- **Standard Implementation**: Extends the abstract class to reuse the state management and item calculation.
- **Custom/Edge Case**: Can implement the interface directly (e.g., `exemptCalculator`) without inheriting the state or overhead of the abstract class.

## Why use this instead of only Default Methods?
Interfaces cannot have:
- Instance fields (state).
- Private fields/constants.
- Specialized `equals`, `hashCode`, or `toString` logic.

The Abstract class "completes" the interface for complex real-world logic.

## How to Run
```bash
mvn compile exec:java
```
