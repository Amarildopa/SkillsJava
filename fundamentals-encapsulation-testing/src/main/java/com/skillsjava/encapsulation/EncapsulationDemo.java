package com.skillsjava.encapsulation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Demo: Encapsulation & Testability.
 * Shows how to handle complex private logic without breaking encapsulation,
 * by extracting it into tested components or using package-private visibility.
 * Refactored to follow all best practices (final, logging).
 */
public final class EncapsulationDemo {

    private static final Logger logger = LoggerFactory.getLogger(EncapsulationDemo.class);

    public static void main(String[] args) {
        logger.info("=== Encapsulation & Testability Demo ===\n");

        Customer vitor = new Customer(true);
        Order order = new Order(vitor, new BigDecimal("1200"));

        DiscountService discountService = new DiscountService();
        BigDecimal discount = discountService.calculateDiscount(order);

        logger.info("Order total: 1200");
        logger.info("Is VIP: {}", vitor.isVip());
        logger.info("Calculated Discount: {}", discount);
        logger.info("\nNote: The 'BaseDiscountPolicy' logic was extracted from ");
        logger.info("'DiscountService' to be independently testable without ");
        logger.info("making the service internals public.");
    }
}

/**
 * Domain Objects
 */
record Customer(boolean isVip) {
}

record Order(Customer customer, BigDecimal total) {
    public Order {
        Objects.requireNonNull(customer, "Customer cannot be null");
        Objects.requireNonNull(total, "Total cannot be null");
    }
}

/**
 * Main Service
 */
final class DiscountService {
    private static final Logger logger = LoggerFactory.getLogger(DiscountService.class);

    // Composition over inheritance
    private final BaseDiscountPolicy policy = new BaseDiscountPolicy();

    public BigDecimal calculateDiscount(Order order) {
        logger.debug("Calculating discount for order: {}", order);
        BigDecimal total = order.getTotal();
        // Uses the policy which is separately testable
        BigDecimal base = policy.compute(order.getCustomer(), total);

        // Additional local logic
        if (total.compareTo(new BigDecimal("1000")) > 0) {
            return base.add(total.multiply(new BigDecimal("0.02")));
        }
        return base;
    }
}

/**
 * Extracted Logic Piece
 * 
 * Instead of having this as a private method inside DiscountService,
 * we extract it here. Now 'DiscountService' remains clean, and we can
 * test this policy in isolation.
 */
final class BaseDiscountPolicy {

    /**
     * Package-private visibility (default) allows tests in the same package
     * to access this without making it 'public'.
     * 
     * @VisibleForTesting (conceptual)
     */
    BigDecimal compute(Customer c, BigDecimal total) {
        return c.isVip()
                ? total.multiply(new BigDecimal("0.05"))
                : BigDecimal.ZERO;
    }
}
