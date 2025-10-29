package org.restaurante.pedidos.Patterns.strategy;


import lombok.extern.slf4j.Slf4j;
import org.restaurante.pedidos.models.Order;

import java.math.BigDecimal;

@Slf4j
public class DigitalPaymentStrategy implements PaymentStrategy {

    private String provider; // "Nequi", "Daviplata", "Nu".
    private String phoneNumber;

    public DigitalPaymentStrategy(String provider, String phoneNumber) {
        this.provider = provider;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean processPayment(Order order, BigDecimal amount) {
        log.info("📱 Procesando pago DIGITAL");
        log.info("   Proveedor: " + provider);
        log.info("   Teléfono: " + phoneNumber);
        log.info("   Monto: $" + amount);
        log.info("   Generando código QR...");

        // Simulación
        try {
            Thread.sleep(1500);
            log.info("   ✅ Pago confirmado por " + provider);
            return true;
        } catch (InterruptedException e) {
            log.info("   ❌ Error en la transacción");
            return false;
        }
    }

    @Override
    public String getPaymentType() {
        return "DIGITAL_" + provider.toUpperCase();
    }
}
