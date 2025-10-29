package org.restaurante.pedidos.Patterns.factory;

import org.restaurante.pedidos.models.Category;
import org.restaurante.pedidos.models.Product;

import java.math.BigDecimal;
import java.util.Arrays;

public class RegularMenuFactory extends MenuFactory {

    @Override
    public Product createProduct(String name) {
        Category category = Category.builder()
                .id(1L)
                .name("Platos Regulares")
                .description("Menú tradicional")
                .build();

        Product product = Product.builder()
                .name(name)
                .category(category)
                .available(true)
                .build();

        switch (name.toLowerCase()) {
            case "bandeja paisa":
                product.setPrice(new BigDecimal("25000"));
                product.setDescription("Plato típico colombiano completo");
                product.setIngredients(Arrays.asList("Carne", "Chorizo", "Frijoles", "Arroz", "Huevo"));
                break;
            case "ajiaco":
                product.setPrice(new BigDecimal("18000"));
                product.setDescription("Sopa típica bogotana");
                product.setIngredients(Arrays.asList("Pollo", "Papa", "Mazorca", "Guascas"));
                break;
            default:
                product.setPrice(new BigDecimal("15000"));
                product.setDescription("Plato del menú regular");
        }

        return product;
    }
}