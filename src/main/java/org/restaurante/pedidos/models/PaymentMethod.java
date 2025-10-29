package org.restaurante.pedidos.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentMethod {

    private long id;
    private String name;
    private String description;
    private String provider;

}
