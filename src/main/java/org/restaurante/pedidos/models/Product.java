package org.restaurante.pedidos.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private BigDecimal discount;
    private Category category;
    private List<String> ingredients;
    private String photo;
    private boolean available;
}
