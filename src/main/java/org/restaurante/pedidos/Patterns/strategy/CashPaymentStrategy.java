package org.restaurante.pedidos.Patterns.strategy;

import lombok.extern.slf4j.Slf4j;
import org.restaurante.pedidos.models.Order;

import java.math.BigDecimal;

@Slf4j
public class CashPaymentStrategy implements PaymentStrategy {

    @Override
    public boolean processPayment(Order order, BigDecimal amount) {
        log.info("💵 Procesando pago en EFECTIVO");
        log.info("   Monto: $" + amount);
        log.info("   Mesa: " + order.getTable());
        log.info("   ✅ Pago en efectivo recibido");
        return true;
    }

    @Override
    public String getPaymentType() {
        return "EFECTIVO";
    }
}