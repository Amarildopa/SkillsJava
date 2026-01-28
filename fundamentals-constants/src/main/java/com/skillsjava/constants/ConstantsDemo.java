package com.skillsjava.constants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.math.BigDecimal;

/**
 * Demo: Constants without Hacks.
 * Demonstrates the right way to define constants in Java,
 * avoiding common antipatterns like Magic Numbers and Constant Interfaces.
 * Refactored to follow all best practices (final classes, logging).
 */
public final class ConstantsDemo {

    private static final Logger logger = LoggerFactory.getLogger(ConstantsDemo.class);

    // 1. Fixing Magic Numbers - Use descriptive names
    private static final int LEGAL_DRINKING_AGE = 21;

    public static void main(String[] args) {
        logger.info("=== Constants without Hacks Demo ===\n");

        // Usage of named constants vs Magic Numbers
        int userAge = 20;
        checkEligibility(userAge);

        // 2. Using Utility Class for Constants
        logger.info("Sales Tax Rate: {}", TaxRates.SALES_TAX);

        // 3. Using Enums for Discrete Values
        logger.info("Default Currency: {}", Currency.BRL);

        for (Currency currency : Currency.values()) {
            logger.info("Available Currency: {} ({})", currency, currency.getSymbol());
        }
    }

    private static void checkEligibility(int age) {
        // WRONG: if (age >= 21)
        // RIGHT:
        if (age >= LEGAL_DRINKING_AGE) {
            logger.info("User is eligible.");
        } else {
            logger.warn("User is NOT eligible (Age must be at least {}).", LEGAL_DRINKING_AGE);
        }
    }
}

/**
 * RIGHT: Final utility class for grouping constants.
 */
final class TaxRates {
    public static final BigDecimal SALES_TAX = new BigDecimal("0.18");

    private TaxRates() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}

/**
 * RIGHT: Enum for named discrete sets.
 */
enum Currency {
    USD("$"),
    EUR("€"),
    BRL("R$");

    private final String symbol;

    Currency(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}

/**
 * WRONG: Constant Interface Antipattern.
 * Note: Keeping this for educational purposes in the demo.
 */
interface BadTaxRates {
    BigDecimal SALES_TAX = new BigDecimal("0.18");
}
