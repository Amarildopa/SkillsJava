# Skill: Inheritance Hazards

This project demonstrates why inheritance is often called a "dangerous" tool and how to use modern Java features to mitigate its risks.

## The Pitfalls

### 1. Overridable Methods in Constructors
The absolute #1 rule of safe inheritance: **Never call an overridable method inside a constructor.**
- In Java, the superclass constructor runs before the subclass.
- If the superclass calls a method that the subclass overrides, the subclass implementation will run while the subclass fields are still `null`.

### 2. The Self-Use Trap
When methods within the same class call each other (e.g., `addBatch` calling `addItem`), subclasses that override one might unintentionally break the other. This is why `@implSpec` documentation is important for classes designed for inheritance.

## The Solutions

### 1. Prohibit Inheritance (`final`)
The easiest way to avoid these bugs is to mark your classes as `final`. If a class is not designed to be extended, don't allow it.

### 2. Sealed Classes (Java 17+)
Sealed classes allow you to specify exactly which classes can extend yours. This provides the flexibility of inheritance with the safety of a closed system.

### 3. Composition over Inheritance
Instead of extending a class, wrap it (Decorator pattern). This keeps encapsulation intact.

## How to Run
```bash
mvn compile exec:java
```
