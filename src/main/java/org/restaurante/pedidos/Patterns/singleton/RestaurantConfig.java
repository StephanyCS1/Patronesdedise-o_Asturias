package org.restaurante.pedidos.Patterns.singleton;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.time.LocalTime;

@Slf4j
public class RestaurantConfig {

    // Instancia única (Singleton)
    private static RestaurantConfig instance;

    private String restaurantName;
    private String address;
    private BigDecimal taxRate;
    private BigDecimal serviceCharge;
    private LocalTime openingTime;
    private LocalTime closingTime;
    private int maxTables;

    // Constructor privado
    private RestaurantConfig() {
        // Configuración por defecto
        this.restaurantName = "El Buen Sabor";
        this.address = "Calle Principal #123";
        this.taxRate = new BigDecimal("0.19"); // 19% IVA
        this.serviceCharge = new BigDecimal("0.10"); // 10% propina sugerida
        this.openingTime = LocalTime.of(11, 0);
        this.closingTime = LocalTime.of(23, 0);
        this.maxTables = 20;
    }

    // Método para obtener la instancia (Thread-safe)
    public static synchronized RestaurantConfig getInstance() {
        if (instance == null) {
            instance = new RestaurantConfig();
        }
        return instance;
    }

    // Getters y Setters
    public String getRestaurantName() { return restaurantName; }
    public void setRestaurantName(String name) { this.restaurantName = name; }

    public BigDecimal getTaxRate() { return taxRate; }
    public void setTaxRate(BigDecimal rate) { this.taxRate = rate; }

    public BigDecimal getServiceCharge() { return serviceCharge; }
    public void setServiceCharge(BigDecimal charge) { this.serviceCharge = charge; }

    public boolean isOpen() {
        LocalTime now = LocalTime.now();
        return now.isAfter(openingTime) && now.isBefore(closingTime);
    }

    public BigDecimal calculateTax(BigDecimal amount) {
        return amount.multiply(taxRate);
    }

    public BigDecimal calculateServiceCharge(BigDecimal amount) {
        return amount.multiply(serviceCharge);
    }

    public void displayConfig() {
        log.info("=== Configuración del Restaurante ===");
        log.info("Nombre: " + restaurantName);
        log.info("Dirección: " + address);
        log.info("IVA: " + taxRate.multiply(BigDecimal.valueOf(100)) + "%");
        log.info("Propina sugerida: " + serviceCharge.multiply(BigDecimal.valueOf(100)) + "%");
        log.info("Horario: " + openingTime + " - " + closingTime);
        log.info("Mesas disponibles: " + maxTables);
        log.info("Estado: " + (isOpen() ? "ABIERTO" : "CERRADO"));
    }
    
}
