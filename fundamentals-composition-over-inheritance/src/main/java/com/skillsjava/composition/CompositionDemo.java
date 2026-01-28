package com.skillsjava.composition;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

/**
 * Demo: Composition vs Inheritance (Pill #39).
 * Demonstrates why inheritance is fragile for behavior interception
 * (validation/auditing)
 * and why the Decorator Pattern (Composition) is more robust.
 */
public final class CompositionDemo {

    private static final Logger logger = LoggerFactory.getLogger(CompositionDemo.class);

    public static void main(String[] args) {
        logger.info("=== Composition over Inheritance Demo (Pill #39) ===\n");

        // Scenario 1: Fragile Inheritance
        logger.info("--- Scenario 1: Fragile Inheritance ---");
        OrderService inheritanceService = new InheritedValidatedOrderService();
        Order order1 = new Order("ORD-001", 100.0);

        logger.info("Invoking save()...");
        inheritanceService.save(order1);

        logger.info("Invoking saveAll()...");
        inheritanceService.saveAll(List.of(new Order("ORD-002", 200.0)));
        logger.warn("BUG: In Scenario 1, saveAll() bypassed validation! (No validation log for ORD-002)\n");

        // Scenario 2: Robust Decorator (Composition)
        logger.info("--- Scenario 2: Robust Decorator (Composition) ---");
        OrderService baseService = new SimpleOrderService();
        OrderService decoratorService = new ValidatedOrderDecorator(baseService);

        logger.info("Invoking save()...");
        decoratorService.save(order1);

        logger.info("Invoking saveAll()...");
        decoratorService.saveAll(List.of(new Order("ORD-003", 300.0)));
        logger.info("SUCCESS: In Scenario 2, saveAll() forced validation through the interface contract.\n");
    }
}

/**
 * Domain Model
 */
record Order(String id, double amount) {
}

/**
 * Interface defining the contract.
 */
interface OrderService {
    void save(Order order);

    void saveAll(List<Order> orders);
}

/**
 * Base implementation.
 */
class SimpleOrderService implements OrderService {
    private static final Logger logger = LoggerFactory.getLogger(SimpleOrderService.class);

    @Override
    public void save(Order order) {
        logger.info("[DB] Saving order: {}", order.id());
    }

    @Override
    public void saveAll(List<Order> orders) {
        // Optimized internal implementation (might not call save() individually)
        logger.info("[DB] Batch saving {} orders...", orders.size());
    }
}

/**
 * ❌ FRAGILE: Inheritance approach.
 * If OrderService adds new methods, this subclass might bypass them.
 */
class InheritedValidatedOrderService extends SimpleOrderService {
    private static final Logger logger = LoggerFactory.getLogger(InheritedValidatedOrderService.class);

    @Override
    public void save(Order order) {
        validate(order);
        super.save(order);
    }

    private void validate(Order order) {
        logger.info("[VALIDATION] Checking credit for order: {}", order.id());
    }

    // BUG: Developer forgot to override saveAll() or didn't know it was added to
    // the base class!
}

/**
 * ✅ ROBUST: Decorator approach.
 * Explicitly implements the interface, protecting the contract.
 */
final class ValidatedOrderDecorator implements OrderService {
    private static final Logger logger = LoggerFactory.getLogger(ValidatedOrderDecorator.class);
    private final OrderService delegate;

    public ValidatedOrderDecorator(OrderService delegate) {
        this.delegate = delegate;
    }

    @Override
    public void save(Order order) {
        validate(order);
        delegate.save(order);
    }

    @Override
    public void saveAll(List<Order> orders) {
        // The interface contract forces us to handle saveAll()
        orders.forEach(this::validate);
        delegate.saveAll(orders);
    }

    private void validate(Order order) {
        logger.info("[VALIDATION] Checking credit for order: {}", order.id());
    }
}
