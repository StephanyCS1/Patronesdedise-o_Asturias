package org.restaurante.pedidos.Patterns.builder;

import org.restaurante.pedidos.Patterns.observer.CustomerNotificationObserver;
import org.restaurante.pedidos.Patterns.observer.KitchenObserver;
import org.restaurante.pedidos.Patterns.observer.OrderObserver;
import org.restaurante.pedidos.Patterns.observer.WaiterObserver;
import org.restaurante.pedidos.enums.OrderStatus;
import org.restaurante.pedidos.models.Items;
import org.restaurante.pedidos.models.Order;
import org.restaurante.pedidos.models.Product;
import org.restaurante.pedidos.models.Waitress;

import java.util.ArrayList;
import java.util.List;

public class OrderBuilder {

    private Long id;
    private List<Items> items;
    private Waitress waitress;
    private String table;
    private OrderStatus status;
    private List<OrderObserver> observers;

    public OrderBuilder() {
        this.items = new ArrayList<>();
        this.observers = new ArrayList<>();
        this.status = OrderStatus.PENDING;
    }

    public OrderBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public OrderBuilder setTable(String table) {
        this.table = table;
        return this;
    }

    public OrderBuilder setWaitress(Waitress waitress) {
        this.waitress = waitress;
        return this;
    }

    public OrderBuilder addItem(Product product, int quantity) {
        Items item = Items.builder()
                .product(product)
                .quantity(quantity)
                .build();
        this.items.add(item);
        return this;
    }

    public OrderBuilder addItems(List<Items> items) {
        this.items.addAll(items);
        return this;
    }

    public OrderBuilder addObserver(OrderObserver observer) {
        this.observers.add(observer);
        return this;
    }

    public OrderBuilder addKitchenNotification() {
        this.observers.add(new KitchenObserver());
        return this;
    }

    public OrderBuilder addWaiterNotification() {
        this.observers.add(new WaiterObserver());
        return this;
    }

    public OrderBuilder addCustomerNotification() {
        this.observers.add(new CustomerNotificationObserver());
        return this;
    }

    public Order build() {
        Order order = new Order();
        order.setId(id);
        order.setProducts(items);
        order.setWaitress(waitress);
        order.setTable(table);
        order.setStatus(status);

        order.calculateTotal();

        for (OrderObserver observer : observers) {
            order.addObserver(observer);
        }

        order.setStatus(OrderStatus.PENDING);

        return order;
    }

    public Order buildAndValidate() {
        if (table == null || table.isEmpty()) {
            throw new IllegalStateException("El pedido debe tener una mesa asignada");
        }

        if (items.isEmpty()) {
            throw new IllegalStateException("El pedido debe tener al menos un item");
        }

        if (waitress == null) {
            throw new IllegalStateException("El pedido debe tener un mesero asignado");
        }

        return build();
    }
}