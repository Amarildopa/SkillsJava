# Skill: Constants without Hacks

This project demonstrates the best practices for defining constants in Java, moving away from common pitfalls.

## Core Principles

### 1. No Magic Numbers
Always give a name to literals. `21` doesn't mean anything to a reader, but `LEGAL_DRINKING_AGE` does.

### 2. No Constant Interfaces
Interfaces define **behavior**, not just static data. Using them for constants pollutes the inheritance tree (if a class implements it) and the API.

### 3. Utility Classes for Constants
Group related constants in a `final` class with a `private constructor`. Use `static final` fields in `SCREAMING_SNAKE_CASE`.

### 4. Enums for Sets
When you have a fixed set of named values (like Currencies, Statuses, Days of the week), use `enum`. It's type-safe and more powerful than simple constants.

## How to Run
```bash
mvn compile exec:java
```
