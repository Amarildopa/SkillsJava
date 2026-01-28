# ☕ SkillsJava - Advanced Agentic Learning Path

Welcome to the **SkillsJava** repository. This is not just a collection of code examples, but a **living knowledge base** designed for **Advanced Agentic Coding**. It combines theoretical best practices (Guardrails) with practical Proof of Concepts (PoCs).

---

## 🏗️ Architecture: Skills vs. Examples

This repository is divided into two main layers:

1.  **The Brain (`.agent/skills/`)**: These are markdown-based guardrails that define the "Right vs. Wrong" for specific Java patterns. They are designed to be consumed by AI Agents (like Antigravity, Cursor, or GitHub Copilot) to ensure code quality.
2.  **The Muscles (`fundamentals-*/`)**: These are mini-projects (PoCs) that implement the rules defined in the Skills. Each project is a standalone, runnable example of a specific Java "Pill".

---

## 📥 Getting Started

You can consume this repository in two ways depending on your needs:

### Option 1: Full Experience (Skills + Examples)
Perfect for learning and exploring the implementations. This will download the entire knowledge base and all runnable PoCs.

```bash
# Clone the repository
git clone https://github.com/Amarildopa/SkillsJava.git

# Enter the directory
cd SkillsJava
```

### Option 2: Agentic Integration (Guidelines Only)
Perfect for "teaching" your own AI agent these best practices in a different project without the example code.

1.  Create a folder named `.agent/skills` in your target project.
2.  Copy the contents of `https://github.com/Amarildopa/SkillsJava/tree/main/.agent/skills` into it.
3.  Point your AI agent to these files as mandatory instructions for any Java development.

---

## 🚀 Running the Demos

Each project is managed by Maven and optimized for Java 21.

### Example: Running the Advanced Date & Time PoC (John McClane)
```bash
cd fundamentals-advanced-datetime
mvn compile exec:java
```

> **Tip**: Check the `run-demo.md` workflow in `.agent/workflows/` for a full list of execution commands.

---

## 💊 Implemented Knowledge Pills (38 - 43+)

| Pill # | Skill Name | Core Concept |
| :--- | :--- | :--- |
| **#43** | [Inheritance Hazards](./.agent/skills/java-skills/rule-inheritance-hazards.md) | Dangers of `extends` and safe-guards with `final/sealed`. |
| **#42** | [Service Interfaces](./.agent/skills/java-skills/rule-service-interfaces.md) | Eliminating `XServiceImpl` bureaucracy. |
| **#41** | [Modern Identifiers](./.agent/skills/java-skills/rule-modern-identifiers.md) | UUIDv7 vs ULID for database performance. |
| **#40** | [Monotonic Clock](./.agent/skills/java-skills/rule-monotonic-clock.md) | Using `nanoTime()` for precision latency measurement. |
| **#39** | [Composition vs Inheritance](./.agent/skills/java-skills/rule-composition-over-inheritance.md) | The Decorator Pattern for robust behavior interception. |
| **#38** | [Advanced Date & Time](./.agent/skills/java-skills/rule-advanced-datetime.md) | Cross-timezone calculations and `Duration` vs `Period`. |

---

## 🛠️ Prerequisites

*   **Java**: 21 or higher (Uses Records, Sealed Classes, and Sequenced Collections).
*   **Maven**: 3.9+ (For dependency management and execution).
*   **Logging**: All demos use **SLF4J + Logback** for professional output.

---

**Maintained with ❤️ for the Java Community.**
