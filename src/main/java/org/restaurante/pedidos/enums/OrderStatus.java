package org.restaurante.pedidos.enums;

public enum OrderStatus {

    PENDING("Pendiente"),
    IN_PREPARATION("En Preparación"),
    READY("Listo"),
    DELIVERED("Entregado"),
    PAID("Pagado"),
    CANCELLED("Cancelado");

    private final String description;

    OrderStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
