package com.skillsjava.template;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.math.BigDecimal;
import java.util.List;

/**
 * Demo: Interface Template Method.
 * Combining interfaces, default methods, and immutable data.
 * Refactored to follow all best practices (final classes, logging).
 */
public final class TemplateMethodDemo {

    private static final Logger logger = LoggerFactory.getLogger(TemplateMethodDemo.class);

    public static void main(String[] args) {
        logger.info("=== Interface Template Method Demo ===\n");

        Cart cart = new Cart(List.of(
                new Item("Java Book", new BigDecimal("50.00")),
                new Item("Clean Code", new BigDecimal("40.00"))));

        // Use Brazilian Tax Policy
        TaxCalculator brTax = new BrazilTaxCalculator();
        BigDecimal totalWithBrTax = brTax.calculateTotalWithTax(cart);
        logger.info("Total with Brazil Tax (5%): {}", totalWithBrTax);

        // Use US Tax Policy
        TaxCalculator usTax = new USTaxCalculator();
        BigDecimal totalWithUsTax = usTax.calculateTotalWithTax(cart);
        logger.info("Total with US Tax (10%): {}", totalWithUsTax);
    }
}

/**
 * Domain Models (Immutable)
 */
record Item(String name, BigDecimal price) {
}

record Cart(List<Item> items) {
    public BigDecimal getSubtotal() {
        return items.stream()
                .map(Item::price)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}

/**
 * The "Template" Interface.
 * Defines the skeleton of an algorithm in a default method.
 */
interface TaxCalculator {
    // This is the variable part that implementations must provide
    BigDecimal getTaxRate();

    /**
     * The Template Method.
     * It's default, so it's inherited by all implementations.
     */
    default BigDecimal calculateTotalWithTax(Cart cart) {
        BigDecimal subtotal = cart.getSubtotal();
        BigDecimal taxAmount = subtotal.multiply(getTaxRate());
        return subtotal.add(taxAmount);
    }
}

/**
 * Specific Implementations
 */
final class BrazilTaxCalculator implements TaxCalculator {
    @Override
    public BigDecimal getTaxRate() {
        return new BigDecimal("0.05"); // 5%
    }
}

final class USTaxCalculator implements TaxCalculator {
    @Override
    public BigDecimal getTaxRate() {
        return new BigDecimal("0.10"); // 10%
    }
}
