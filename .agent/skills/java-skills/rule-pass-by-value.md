# Guardrail: Java Pass-by-Value

## Context
A common misconception in Java is that objects are passed by reference. In reality, **Java always passes by value**. Understanding the difference between passing a primitive value and passing a copy of an object's reference is crucial for predictable code.

## Guardrails

### 1. Primitives vs. Objects
- **RULE**: Remember that for primitives, the "value" is the data itself. For objects, the "value" is the memory address (reference).
- **PRACTICE**: 
    - When passing an object, both the caller and the method point to the same object in the heap.
    - Modifying the object's internal state (e.g., `list.add("item")`) **will** be reflected in the caller.
- **REASON**: You are operating on the same instance in memory.

### 2. Variable Reassignment
- **RULE**: Reassigning a parameter variable inside a method (e.g., `obj = new MyObject()`) **will NOT** affect the original variable in the caller.
- **PRACTICE**: Do not expect a method to "replace" an object instance passed as a parameter.
- **REASON**: The method works with a copy of the reference. Changing the copy only points the local variable to a different address, leaving the caller's original reference untouched.

### 3. Swap Pattern
- **RULE**: Generic `swap(a, b)` methods do not work for object references in Java.
- **PRACTICE**: To swap two values, you must return a wrapper object, use a collection, or perform the swap at the caller level.
- **REASON**: Swapping parameter variables only swaps local copies.

### 4. Use of `final` for Parameters
- **RULE**: Using `final` on parameters only prevents **reassignment** of the local variable; it does **not** prevent the modification of the object's internal state.
- **PRACTICE**: Use `final` to signal intent that the parameter should not be reassigned, but do not rely on it for immutability of the object itself.
- **REASON**: `final` only locks the reference, not the heap data.

## Reference Demo
See implementation in: `fundamentals-pass-by-value/`

---
**Author:** Wanderlei Souza
