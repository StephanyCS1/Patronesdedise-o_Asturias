package org.restaurante.pedidos.Patterns.factory;

import org.restaurante.pedidos.models.Category;
import org.restaurante.pedidos.models.Product;

import java.math.BigDecimal;
import java.util.Arrays;

public class PremiumMenuFactory extends MenuFactory {

    @Override
    public Product createProduct(String name) {
        Category category = Category.builder()
                .id(3L)
                .name("Menú Premium")
                .description("Platos gourmet")
                .build();

        Product product = Product.builder()
                .name(name)
                .category(category)
                .available(true)
                .build();

        switch (name.toLowerCase()) {
            case "filet mignon":
                product.setPrice(new BigDecimal("45000"));
                product.setDescription("Corte premium de carne");
                product.setIngredients(Arrays.asList("Lomo fino", "Salsa bordelesa", "Papa gratinada"));
                break;
            case "salmón a la parrilla":
                product.setPrice(new BigDecimal("40000"));
                product.setDescription("Salmón fresco con vegetales");
                product.setIngredients(Arrays.asList("Salmón", "Espárragos", "Salsa de eneldo"));
                break;
            default:
                product.setPrice(new BigDecimal("35000"));
                product.setDescription("Plato premium");
        }

        return product;
    }
}