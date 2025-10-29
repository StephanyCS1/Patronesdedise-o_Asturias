package org.restaurante.pedidos.Patterns.strategy;

import lombok.extern.slf4j.Slf4j;
import org.restaurante.pedidos.models.Order;

import java.math.BigDecimal;

@Slf4j
public class CardPaymentStrategy implements PaymentStrategy {

    private String cardNumber;
    private String cardHolder;

    public CardPaymentStrategy(String cardNumber, String cardHolder) {
        this.cardNumber = maskCardNumber(cardNumber);
        this.cardHolder = cardHolder;
    }

    @Override
    public boolean processPayment(Order order, BigDecimal amount) {
        log.info("💳 Procesando pago con TARJETA");
        log.info("   Tarjeta: " + cardNumber);
        log.info("   Titular: " + cardHolder);
        log.info("   Monto: $" + amount);
        log.info("   Conectando con banco...");

        // Simulación de procesamiento
        try {
            Thread.sleep(1000);
            log.info("   ✅ Transacción aprobada");
            return true;
        } catch (InterruptedException e) {
            log.info("   ❌ Error en la transacción");
            return false;
        }
    }

    @Override
    public String getPaymentType() {
        return "TARJETA";
    }

    private String maskCardNumber(String cardNumber) {
        if (cardNumber.length() < 4) return "****";
        return "**** **** **** " + cardNumber.substring(cardNumber.length() - 4);
    }
}
