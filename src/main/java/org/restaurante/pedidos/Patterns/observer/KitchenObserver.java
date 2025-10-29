package org.restaurante.pedidos.Patterns.observer;

import lombok.extern.slf4j.Slf4j;
import org.restaurante.pedidos.enums.OrderStatus;
import org.restaurante.pedidos.models.Order;

@Slf4j
public class KitchenObserver implements OrderObserver {

    @Override
    public void update(Order order) {
       log.info("🍳 COCINA NOTIFICADA:");
       log.info("   Pedido #" + order.getId() + " - Mesa: " + order.getTable());
       log.info("   Estado: " + order.getOrderStatus().getDescription());
       log.info("   Items: " + order.getProducts().size());

        if (order.getOrderStatus() == OrderStatus.PENDING) {
           log.info("   ⚠️  Nuevo pedido para preparar!");
        }
    }
}
