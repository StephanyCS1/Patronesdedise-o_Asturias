package org.restaurante.pedidos;

import lombok.extern.slf4j.Slf4j;
import org.restaurante.pedidos.Patterns.builder.OrderBuilder;
import org.restaurante.pedidos.Patterns.factory.MenuFactory;
import org.restaurante.pedidos.Patterns.factory.PremiumMenuFactory;
import org.restaurante.pedidos.Patterns.factory.RegularMenuFactory;
import org.restaurante.pedidos.Patterns.factory.VegetarianMenuFactory;
import org.restaurante.pedidos.Patterns.singleton.RestaurantConfig;
import org.restaurante.pedidos.Patterns.strategy.CardPaymentStrategy;
import org.restaurante.pedidos.Patterns.strategy.CashPaymentStrategy;
import org.restaurante.pedidos.Patterns.strategy.DigitalPaymentStrategy;
import org.restaurante.pedidos.Patterns.strategy.PaymentProcessor;
import org.restaurante.pedidos.enums.OrderStatus;
import org.restaurante.pedidos.models.Order;
import org.restaurante.pedidos.models.Product;
import org.restaurante.pedidos.models.Waitress;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@Slf4j
@SpringBootApplication
public class SistemaPedidosRestauranteApplication {

    public static void main(String[] args) {

        log.info("╔═══════════════════════════════════════════════╗");
        log.info("║  SISTEMA DE GESTIÓN DE PEDIDOS - RESTAURANTE ║");
        log.info("╚═══════════════════════════════════════════════╝");

        // ========================================
        // 1. SINGLETON PATTERN - Configuración
        // ========================================
        log.info("1️⃣  SINGLETON PATTERN - Configuración del Restaurante");
        log.info("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        RestaurantConfig config = RestaurantConfig.getInstance();
        config.displayConfig();
        // Verificar que es la misma instancia
        RestaurantConfig config2 = RestaurantConfig.getInstance();
        log.info("✓ Verificación Singleton: " + (config == config2 ? "ÉXITO (misma instancia)" : "FALLO"));

        // ========================================
        // 2. FACTORY METHOD PATTERN - Crear Productos
        // ========================================
        log.info("2️⃣  FACTORY METHOD PATTERN - Creación de Productos");
        log.info("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        // Factory para menú regular
        MenuFactory regularFactory = new RegularMenuFactory();
        Product bandejaPaisa = regularFactory.createProduct("Bandeja Paisa");
        log.info("✓ Creado: " + bandejaPaisa.getName() + " - $" + bandejaPaisa.getPrice());

        // Factory para menú vegetariano
        MenuFactory vegetarianFactory = new VegetarianMenuFactory();
        Product ensaladaBowl = vegetarianFactory.createProduct("Ensalada Bowl");
        log.info("✓ Creado: " + ensaladaBowl.getName() + " - $" + ensaladaBowl.getPrice());

        // Factory para menú premium
        MenuFactory premiumFactory = new PremiumMenuFactory();
        Product filetMignon = premiumFactory.createProduct("Filet Mignon");
        log.info("✓ Creado: " + filetMignon.getName() + " - $" + filetMignon.getPrice());

        // ========================================
        // 3. BUILDER PATTERN - Construir Pedido
        // ========================================
        log.info("3️⃣  BUILDER PATTERN - Construcción de Pedido");
        log.info("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        // Crear mesero
        Waitress maria = Waitress.builder()
                .id(1L)
                .name("María González")
                .section("Terraza")
                .build();

        log.info("✓ Mesero asignado: " + maria.getName());

        // Construir pedido con Builder Pattern
        OrderBuilder builder = new OrderBuilder();

        Order order = builder
                .setId(1001L)
                .setTable("Mesa 5")
                .setWaitress(maria)
                .addItem(bandejaPaisa, 2)
                .addItem(ensaladaBowl, 1)
                .addItem(filetMignon, 1)
                .addKitchenNotification()      // Observer
                .addWaiterNotification()       // Observer
                .addCustomerNotification()     // Observer
                .buildAndValidate();

        log.info("✓ Pedido #" + order.getId() + " creado para " + order.getTable());
        log.info("✓ Total items: " + order.getProducts().size());
        log.info("✓ Subtotal: $" + order.getTotal());

        // ========================================
        // 4. OBSERVER PATTERN - Notificaciones
        // ========================================
        log.info("4️⃣  OBSERVER PATTERN - Sistema de Notificaciones");
        log.info("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        log.info("\n📋 Cambiando estado a: EN PREPARACIÓN");
        order.setStatus(OrderStatus.IN_PREPARATION);

        log.info("\n📋 Cambiando estado a: LISTO");
        order.setStatus(OrderStatus.READY);

        log.info("\n📋 Cambiando estado a: ENTREGADO");
        order.setStatus(OrderStatus.DELIVERED);

        // ========================================
        // 5. STRATEGY PATTERN - Métodos de Pago
        // ========================================
        log.info("5️⃣  STRATEGY PATTERN - Procesamiento de Pagos");
        log.info("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        PaymentProcessor processor = new PaymentProcessor();

        // Probar pago en efectivo
        log.info("📌 Opción 1: Pago en Efectivo");
        processor.setPaymentStrategy(new CashPaymentStrategy());
        boolean cashResult = processor.executePayment(order);
        log.info("Resultado: " + (cashResult ? "✅ APROBADO" : "❌ RECHAZADO"));

        // Probar pago con tarjeta
        log.info("📌 Opción 2: Pago con Tarjeta");
        processor.setPaymentStrategy(new CardPaymentStrategy("4532123456789012", "Juan Pérez"));
        boolean cardResult = processor.executePayment(order);
        log.info("Resultado: " + (cardResult ? "✅ APROBADO" : "❌ RECHAZADO"));

        // Probar pago digital
        log.info("📌 Opción 3: Pago Digital");
        processor.setPaymentStrategy(new DigitalPaymentStrategy("Nequi", "3001234567"));
        boolean digitalResult = processor.executePayment(order);
        log.info("Resultado: " + (digitalResult ? "✅ APROBADO" : "❌ RECHAZADO"));

        if (digitalResult) {
            order.setStatus(OrderStatus.PAID);
        }

        // ========================================
        // Pedido
        // ========================================
        log.info("╔═══════════════════════════════════════════════╗");
        log.info("║           RESUMEN DE PATRONES APLICADOS      ║");
        log.info("╚═══════════════════════════════════════════════╝");
        log.info("✅ SINGLETON:  Configuración única del restaurante");
        log.info("✅ FACTORY:    Creación flexible de productos por categoría");
        log.info("✅ BUILDER:    Construcción paso a paso de pedidos complejos");
        log.info("✅ OBSERVER:   Notificaciones en tiempo real a cocina/mesero/cliente");
        log.info("✅ STRATEGY:   Múltiples métodos de pago intercambiables");
        log.info("🎯 Estado final del pedido #" + order.getId() + ": " + order.getOrderStatus().getDescription());
        log.info("💰 Total procesado: $" + calculateFinalTotal(order));
        log.info("════════════════════════════════════════════════");
        log.info("     ✨ SISTEMA FUNCIONANDO CORRECTAMENTE ✨");
        log.info("════════════════════════════════════════════════");
    }

    private static BigDecimal calculateFinalTotal(Order order) {
        RestaurantConfig config = RestaurantConfig.getInstance();
        BigDecimal subtotal = order.getTotal();
        BigDecimal tax = config.calculateTax(subtotal);
        BigDecimal serviceCharge = config.calculateServiceCharge(subtotal);
        return subtotal.add(tax).add(serviceCharge);
    }

}
