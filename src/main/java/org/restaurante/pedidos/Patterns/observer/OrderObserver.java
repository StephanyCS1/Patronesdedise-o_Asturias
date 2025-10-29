package org.restaurante.pedidos.Patterns.observer;

import org.restaurante.pedidos.models.Order;

public interface OrderObserver {

    void update(Order order);

}
