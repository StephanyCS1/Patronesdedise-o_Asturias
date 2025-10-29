package org.restaurante.pedidos.Patterns.observer;

import lombok.extern.slf4j.Slf4j;
import org.restaurante.pedidos.enums.OrderStatus;
import org.restaurante.pedidos.models.Order;

@Slf4j
public class CustomerNotificationObserver implements OrderObserver {
    @Override
    public void update(Order order) {
        log.info("📱 NOTIFICACIÓN AL CLIENTE:");
        log.info("   Su pedido #" + order.getId());
        log.info("   Estado actual: " + order.getOrderStatus().getDescription());

        if (order.getOrderStatus() == OrderStatus.READY) {
            log.info("   🎉 ¡Su pedido está listo!");
        }
    }
}
