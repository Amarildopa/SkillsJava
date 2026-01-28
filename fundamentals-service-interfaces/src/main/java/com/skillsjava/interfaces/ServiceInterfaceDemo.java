package com.skillsjava.interfaces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

/**
 * Demo: Service Interfaces vs XServiceImpl.
 * Shows when to use interfaces (multiple implementations) and when to use
 * direct classes.
 * Refactored to follow all best practices (final classes, logging).
 */
public final class ServiceInterfaceDemo {

    private static final Logger logger = LoggerFactory.getLogger(ServiceInterfaceDemo.class);

    public static void main(String[] args) {
        logger.info("=== Service Interfaces Demo ===\n");

        // 1. Concrete Service (No unnecessary interface)
        UserService userService = new UserService();
        userService.register("alice@example.com");

        // 2. Legitimate Interface (Strategy Pattern)
        NotificationService email = new EmailProvider();
        NotificationService sms = new SmsProvider();

        logger.info("\n--- Multicast Notification ---");
        List.of(email, sms).forEach(provider -> provider.send("Hello World"));

        // 3. ISP (Interface Segregation Principle)
        ShippingCalculator calculator = new FedExCalculator();
        logger.info("\nFedEx Shipping: {}", calculator.calculate(10.5));
    }
}

/**
 * RIGHT: Direct class for logic with no variation.
 */
final class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public void register(String email) {
        logger.info("Registering user: {}", email);
    }
}

/**
 * RIGHT: Interface for logic with multiple implementations.
 */
interface NotificationService {
    void send(String message);
}

final class EmailProvider implements NotificationService {
    private static final Logger logger = LoggerFactory.getLogger(EmailProvider.class);

    @Override
    public void send(String message) {
        logger.info("[Email] Sending: {}", message);
    }
}

final class SmsProvider implements NotificationService {
    private static final Logger logger = LoggerFactory.getLogger(SmsProvider.class);

    @Override
    public void send(String message) {
        logger.info("[SMS] Sending: {}", message);
    }
}

/**
 * RIGHT: Small, focused interface (ISP).
 */
interface ShippingCalculator {
    double calculate(double weight);
}

final class FedExCalculator implements ShippingCalculator {
    @Override
    public double calculate(double weight) {
        return weight * 1.2;
    }
}
