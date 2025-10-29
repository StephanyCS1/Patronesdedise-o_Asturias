package org.restaurante.pedidos.models;

import lombok.*;
import org.restaurante.pedidos.enums.OrderStatus;
import org.restaurante.pedidos.Patterns.observer.OrderObserver;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private Long id;
    private List<Items> products;
    private Waitress waitress;
    private BigDecimal total;
    private String table;
    private PaymentMethod paymentMethod;
    private OrderStatus orderStatus;

    @Builder.Default
    private  List<OrderObserver> observers = new ArrayList<>();

    public void addObserver(OrderObserver observer) {
        observers.add(observer);
    }

    public void setStatus(OrderStatus status){
        this.orderStatus = status;
        notifyObservers();
    }

    private void notifyObservers() {
        for (OrderObserver observer : observers) {
            observer.update(this);
        }
    }

    public void calculateTotal(){
        this.total = products.stream()
                .map(Items::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
