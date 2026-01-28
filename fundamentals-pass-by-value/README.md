# Skill: Java Pass-by-Value

This project clarifies one of the most persistent myths in the Java ecosystem: the mechanism of parameter passing.

## The Core Concept: Always Pass-by-Value

Java **always** passes arguments by value.

### 1. For Primitives
The value is the actual binary data. When you pass an `int`, you are passing a copy of that integer. Changing the copy does not affect the original.

### 2. For Objects
The value is the destination address (the reference). 
- When you pass an object, Java copies the **reference** (the address) and passes that copy to the method.
- Because both references (the original and the copy) point to the same object on the heap, calling methods that change the object's state (like `list.add()`) will be visible to the caller.
- **BUT**, if you reassign the reference in the method (`list = new ArrayList()`), you are only changing where the local copy points. The original reference in the caller still points to the old address.

## Guardrails Implemented

- **No Reassignment Expectations**: Never expect a method to replace an object instance for the caller.
- **Fail-Safe Mutability**: Be aware that `final` on parameters only protects the reference pointer, not the data it points to.
- **No Swap Methods**: Don't try to implement a generic swap; it is impossible in Java without wrappers.

## How to Run
```bash
mvn compile exec:java
```
