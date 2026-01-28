# Skill: Useful Interfaces vs. XServiceImpl Antipattern

This project focuses on the practical application of Interfaces in Java, emphasizing that they should be used as functional contracts rather than empty architectural requirements.

## Key Learnings

### 1. Kill the `Impl` Suffix
If you only have one way to do something (one implementation), just use a class. 
- **Wrong**: `UserService` (interface) + `UserServiceImpl` (class).
- **Right**: `UserService` (class).

### 2. Interface Segregation Principle (ISP)
"Clients should not be forced to depend upon interfaces that they do not use."
- Instead of a huge `OrderService` interface, use smaller, specific interfaces like `ShippingCalculator` or `InvoiceIssuer`.
- This makes your code more modular, easier to test, and more resilient to change.

### 3. Purposeful Polymorphism
Use interfaces only when you have multiple implementations (e.g., `EmailProvider`, `SmsProvider`) or when defining a clear boundary between modules.

## How to Run
```bash
mvn compile exec:java
```
