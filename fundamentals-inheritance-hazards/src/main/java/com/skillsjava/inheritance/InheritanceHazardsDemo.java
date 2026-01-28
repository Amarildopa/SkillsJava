package com.skillsjava.inheritance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Demo: Inheritance Hazards.
 * Demonstrates common bugs caused by improper inheritance design,
 * such as calling overridable methods in constructors and undocumented
 * self-use.
 * Refactored to follow all best practices (final classes, logging).
 */
public final class InheritanceHazardsDemo {

    private static final Logger logger = LoggerFactory.getLogger(InheritanceHazardsDemo.class);

    public static void main(String[] args) {
        logger.info("=== Inheritance Hazards Demo ===\n");

        // 1. Problem: Overridable method in constructor
        logger.info("--- Scenario 1: Constructor Pitfall ---");
        try {
            new SubClass();
        } catch (Exception e) {
            logger.error("Caught Expected Error (NullPointerException in Constructor): {}", e.toString());
        }

        // 2. Problem: Undocumented self-use in methods
        logger.info("\n--- Scenario 2: Undocumented Self-Use ---");
        ShoppingCart cart = new ShoppingCart();
        cart.addItem("Item 1");
        cart.addBatch(List.of("Item 2", "Item 3"));
        logger.info("Standard Cart total items: {}", cart.getItemCount());

        DiscountCart discountCart = new DiscountCart();
        discountCart.addItem("Item 1");
        discountCart.addBatch(List.of("Item 2", "Item 3"));
        logger.info("Discount Cart total items: {}", discountCart.getItemCount());
        logger.info("Notice: If addBatch calls addItem, and addItem is overridden, logic runs multiple times!");

        // 3. Solution: Sealed Classes (Java 17+)
        logger.info("\n--- Scenario 3: Sealed Classes (Modern Java) ---");
        Payment pix = new PixPayment();
        logger.info("Payment type: {}", pix.getClass().getSimpleName());
    }
}

/**
 * DANGER: Calling overridable method in constructor.
 */
class BaseClass {
    private static final Logger logger = LoggerFactory.getLogger(BaseClass.class);

    BaseClass() {
        setup();
    }

    protected void setup() {
        logger.info("Base setup");
    }
}

final class SubClass extends BaseClass {
    private static final Logger logger = LoggerFactory.getLogger(SubClass.class);
    private final Instant timestamp;

    SubClass() {
        super();
        this.timestamp = Instant.now();
    }

    @Override
    protected void setup() {
        // This is called BEFORE SubClass's field 'timestamp' is initialized!
        logger.info("Sub setup. Timestamp: {}", timestamp.toString());
    }
}

/**
 * DANGER: Undocumented self-use.
 */
class ShoppingCart {
    private final List<String> items = new ArrayList<>();
    private int counter = 0;

    public void addItem(String item) {
        items.add(item);
        counter++;
    }

    public void addBatch(List<String> newItems) {
        newItems.forEach(this::addItem);
    }

    public int getItemCount() {
        return counter;
    }
}

final class DiscountCart extends ShoppingCart {
    private static final Logger logger = LoggerFactory.getLogger(DiscountCart.class);
    private int discountAppliedCount = 0;

    @Override
    public void addItem(String item) {
        super.addItem(item);
        applyDiscount();
    }

    private void applyDiscount() {
        discountAppliedCount++;
        logger.info("Discount applied ({})", discountAppliedCount);
    }
}

/**
 * SOLUTION: Sealed Classes (Compiler-level control).
 */
sealed class Payment permits PixPayment, CreditCardPayment {
}

final class PixPayment extends Payment {
}

final class CreditCardPayment extends Payment {
}
