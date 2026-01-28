---
description: How to run the Java Skill demos
---

This workflow explains how to execute the mini-projects created for each Java Skill.

1. Ensure you have Java 21 and Maven installed and configured in your PATH.
// turbo
2. Check environment:
```bash
java -version
mvn -version
```

3. To run a specific demo, navigate to its folder and use the following command:
```bash
mvn compile exec:java -Dexec.mainClass="com.skillsjava.[package].[ClassName]"
```

Example for Date & Time:
```bash
cd fundamentals-datetime
mvn compile exec:java -Dexec.mainClass="com.skillsjava.datetime.DateTimeDemo"
```

Example for NPE Avoidance:
```bash
cd fundamentals-npe-avoidance
mvn compile exec:java -Dexec.mainClass="com.skillsjava.npe.NpeAvoidanceDemo"
```

Example for Collections API:
```bash
cd fundamentals-collections
mvn compile exec:java -Dexec.mainClass="com.skillsjava.collections.CollectionsDemo"
```

Example for Combined Showcase:
```bash
cd fundamentals-combined-showcase
mvn compile exec:java -Dexec.mainClass="com.skillsjava.showcase.CombinedShowcaseDemo"
```

Example for Pass-by-Value:
```bash
cd fundamentals-pass-by-value
mvn compile exec:java -Dexec.mainClass="com.skillsjava.value.PassByValueDemo"
```

Example for Constants:
```bash
cd fundamentals-constants
mvn compile exec:java -Dexec.mainClass="com.skillsjava.constants.ConstantsDemo"
```

Example for Encapsulation & Testability:
```bash
cd fundamentals-encapsulation-testing
mvn compile exec:java -Dexec.mainClass="com.skillsjava.encapsulation.EncapsulationDemo"
```

Example for Interface Template Method:
```bash
cd fundamentals-interface-template-method
mvn compile exec:java -Dexec.mainClass="com.skillsjava.template.TemplateMethodDemo"
```

Example for Service Interfaces:
```bash
cd fundamentals-service-interfaces
mvn compile exec:java -Dexec.mainClass="com.skillsjava.interfaces.ServiceInterfaceDemo"
```

Example for Inheritance Hazards:
```bash
cd fundamentals-inheritance-hazards
mvn compile exec:java -Dexec.mainClass="com.skillsjava.inheritance.InheritanceHazardsDemo"
```

Example for Modern Identifiers:
```bash
cd fundamentals-modern-identifiers
mvn compile exec:java -Dexec.mainClass="com.skillsjava.ids.ModernIdentifiersDemo"
```

Example for User Service (Full Showcase):
```bash
cd fundamentals-user-service-showcase
mvn compile exec:java -Dexec.mainClass="com.skillsjava.userservice.UserServiceDemo"
```

Example for Monotonic Clock (Pill #40):
```bash
cd fundamentals-monotonic-clock
mvn compile exec:java -Dexec.mainClass="com.skillsjava.clock.MonotonicClockDemo"
```

Example for Composition over Inheritance (Pill #39):
```bash
cd fundamentals-composition-over-inheritance
mvn compile exec:java -Dexec.mainClass="com.skillsjava.composition.CompositionDemo"
```


