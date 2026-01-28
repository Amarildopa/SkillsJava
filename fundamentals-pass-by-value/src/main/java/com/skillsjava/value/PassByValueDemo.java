package com.skillsjava.value;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * Demo: Java Pass-by-Value.
 * Demonstrates why Java always passes by value, and the difference
 * between primitive values and object references.
 * Refactored to follow all best practices (final classes, logging).
 */
public final class PassByValueDemo {

    private static final Logger logger = LoggerFactory.getLogger(PassByValueDemo.class);

    public static void main(String[] args) {
        logger.info("=== Java Pass-by-Value Demo ===\n");

        // 1. Primitive Demonstration
        int x = 10;
        logger.info("Before modifyPrimitive: x = {}", x);
        modifyPrimitive(x);
        logger.info("After modifyPrimitive:  x = {} (Unchanged)\n", x);

        // 2. Object State Demonstration (Commonly confused as pass-by-reference)
        List<String> list = new ArrayList<>();
        list.add("Original");
        logger.info("Before modifyState: list = {}", list);
        modifyState(list);
        logger.info("After modifyState:  list = {} (Internal state changed)\n", list);

        // 3. Object Reassignment Demonstration (The "Proof" of pass-by-value)
        List<String> myList = new ArrayList<>();
        myList.add("Initial");
        logger.info("Before reassignObject: myList = {}", myList);
        reassignObject(myList);
        logger.info("After reassignObject:  myList = {} (Wait, still 'Initial'? Yes!)\n", myList);

        // 4. Swap Demonstration
        String a = "Apple";
        String b = "Banana";
        logger.info("Before swap: a={}, b={}", a, b);
        swap(a, b);
        logger.info("After swap:  a={}, b={} (Swap failed because we only swapped local copies)\n", a, b);

        // 5. Final Parameter Demonstration
        demonstrateFinalParameter(new ArrayList<>(List.of("A")));
    }

    private static void modifyPrimitive(int value) {
        // 'value' is a copy of 'x'
        value = 20;
    }

    private static void modifyState(List<String> list) {
        // 'list' is a copy of the reference pointing to the same heap object
        list.add("Modified");
    }

    private static void reassignObject(List<String> list) {
        list.add("Visible"); // This affects the caller
        list = new ArrayList<>(); // Now 'list' points to a NEW memory address locally
        list.add("Invisible"); // This does NOT affect the caller's 'myList'
    }

    private static void swap(Object a, Object b) {
        Object temp = a;
        a = b;
        b = temp;
    }

    private static void demonstrateFinalParameter(final List<String> list) {
        list.add("Changed"); // VALID: Can still modify the internal state of the object
        logger.info("Final parameter state modified: {}", list);
    }
}
