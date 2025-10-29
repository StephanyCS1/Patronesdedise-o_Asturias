package org.restaurante.pedidos.Patterns.strategy;

import lombok.extern.slf4j.Slf4j;
import org.restaurante.pedidos.Patterns.singleton.RestaurantConfig;
import org.restaurante.pedidos.models.Order;

import java.math.BigDecimal;

@Slf4j
public class PaymentProcessor {

    private PaymentStrategy strategy;

    public void setPaymentStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public boolean executePayment(Order order) {
        if (strategy == null) {
            log.info("❌ No se ha seleccionado método de pago");
            return false;
        }

        // Calcular monto total con impuestos
        RestaurantConfig config = RestaurantConfig.getInstance();
        BigDecimal subtotal = order.getTotal();
        BigDecimal tax = config.calculateTax(subtotal);
        BigDecimal serviceCharge = config.calculateServiceCharge(subtotal);
        BigDecimal totalAmount = subtotal.add(tax).add(serviceCharge);

        log.info("\n=== Resumen de Pago ===");
        log.info("Subtotal: $" + subtotal);
        log.info("IVA (19%): $" + tax);
        log.info("Propina sugerida (10%): $" + serviceCharge);
        log.info("TOTAL: $" + totalAmount);

        return strategy.processPayment(order, totalAmount);
    }
}