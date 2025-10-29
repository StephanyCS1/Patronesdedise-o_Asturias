package org.restaurante.pedidos.Patterns.strategy;

import org.restaurante.pedidos.models.Order;

import java.math.BigDecimal;

public interface PaymentStrategy {
    boolean processPayment(Order order, BigDecimal amount);
    String getPaymentType();
}