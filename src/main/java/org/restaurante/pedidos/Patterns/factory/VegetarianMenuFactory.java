package org.restaurante.pedidos.Patterns.factory;

import org.restaurante.pedidos.models.Category;
import org.restaurante.pedidos.models.Product;

import java.math.BigDecimal;
import java.util.Arrays;

public class VegetarianMenuFactory extends MenuFactory {

    @Override
    public Product createProduct(String name) {
        Category category = Category.builder()
                .id(2L)
                .name("Menú Vegetariano")
                .description("Opciones sin carne")
                .build();

        Product product = Product.builder()
                .name(name)
                .category(category)
                .available(true)
                .build();

        switch (name.toLowerCase()) {
            case "ensalada bowl":
                product.setPrice(new BigDecimal("22000"));
                product.setDescription("Bowl saludable con quinoa");
                product.setIngredients(Arrays.asList("Quinoa", "Aguacate", "Tomate", "Espinaca"));
                break;
            case "pasta primavera":
                product.setPrice(new BigDecimal("20000"));
                product.setDescription("Pasta con vegetales frescos");
                product.setIngredients(Arrays.asList("Pasta", "Brócoli", "Zanahoria", "Pimentón"));
                break;
            default:
                product.setPrice(new BigDecimal("18000"));
                product.setDescription("Plato vegetariano");
        }

        return product;
    }
}