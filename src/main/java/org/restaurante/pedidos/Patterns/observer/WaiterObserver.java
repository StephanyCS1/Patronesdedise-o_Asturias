package org.restaurante.pedidos.Patterns.observer;

import lombok.extern.slf4j.Slf4j;
import org.restaurante.pedidos.enums.OrderStatus;
import org.restaurante.pedidos.models.Order;

@Slf4j
public class WaiterObserver implements OrderObserver {
    @Override
    public void update(Order order) {
        System.out.println("👤 MESERO NOTIFICADO:");
        System.out.println("   Pedido #" + order.getId() + " - Mesa: " + order.getTable());
        System.out.println("   Estado: " + order.getOrderStatus().getDescription());

        if (order.getOrderStatus() == OrderStatus.READY) {
            log.info("  ✅ Pedido listo para servir!");
        }

    }
}
