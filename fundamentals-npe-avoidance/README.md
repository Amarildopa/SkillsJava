# Skill: NPE Avoidance

Guidelines to prevent NullPointerExceptions and improve code reliability.

## Applied Guardrails:

1.  **Empty Collections**: Methods like `findUsers` return `Collections.emptyList()` instead of `null`.
2.  **Objects.requireNonNull**: Used for mandatory parameters to provide immediate, clear feedback.
3.  **Optional Handling**: Demonstrates the use of `Optional.ifPresent` for clean execution flows.

## How to run:
```bash
mvn compile exec:java -Dexec.mainClass="com.skillsjava.npe.NpeAvoidanceDemo"
```
