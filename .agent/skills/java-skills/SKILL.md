---
name: Java-Skills
description: Best practices guardrails and modern Java patterns extracted from technical articles.
---

# Java-Skills

This document defines the process for creating and organizing Java learning in the `SkillsJava` repository, acting as a mandatory guardrail for all development.

## Objective
Transform theoretical articles into practical mini-projects focused on one "Skill" at a time, establishing clear coding standards.

## Directory Structure
Each skill must have its own root directory:
`fundamentals-[skill-name]/` or `spring-[skill-name]/`

Within each project:
- `pom.xml`: Configured for Java 21+.
- `src/main/java/.../`: Source code with explanatory comments (in English).
- `README.md`: Skill-specific documentation explaining the "Why" behind the code.

## Coding Standards (LLM Optimization)
- **Language**: All code, comments, and documentation MUST be in **English**.
- **Modern Java**: Always prioritize Java 17+ features.
- **No Legacy**: Strictly avoid obsolete classes unless for comparison.

## Consolidated Guardrails
The following links point to specific best practices that MUST be followed during development:

1.  **[Date and Time API](./rule-date-time.md)**: Rules for replacing legacy `Date` and `Calendar` with `java.time`.
2.  **[NPE Avoidance](./rule-npe-avoidance.md)**: Strategies to eliminate NullPointerExceptions using Collections, Objects, and Optional.
3.  **[Collections API](./rule-collections.md)**: Best practices for modern Java collections and immutability.
4.  **[Pass-by-Value](./rule-pass-by-value.md)**: Clarification on Java's parameter passing mechanism.
5.  **[Constants](./rule-constants.md)**: Best practices for defining constants and avoiding antipatterns.
6.  **[Encapsulation & Testability](./rule-encapsulation-testing.md)**: Rules for visibility and how to handle testing of private logic.
7.  **[Interface Template Method](./rule-interface-template-method.md)**: Combining interfaces, default methods, and abstract classes.
8.  **[Service Interfaces (No Impl)](./rule-service-interfaces.md)**: Avoiding the XServiceImpl antipattern and following ISP.
9.  **[Inheritance Hazards](./rule-inheritance-hazards.md)**: Dangers of inheritance, overridable methods in constructors, and the value of final/sealed classes.
10. **[Modern Identifiers](./rule-modern-identifiers.md)**: Performance impact of UUIDv4 vs UUIDv7/ULID in databases.

## Workflow (Antigravity's Operating Procedure)
1. **Article Consumption**: Extract "Right" vs "Wrong" patterns from the provided article.
2. **Dedicated Guardrail Creation**: Create a new `rule-[name].md` file in this directory.
3. **Reference Update**: Add a link to the new rule in this `SKILL.md`.
4. **Demo (Mini-Project)**: Create a mini-project as a Proof of Concept (PoC) for the Skill.
5. **Validation**: Ensure all future code does not violate these established Guardrails.
