package org.restaurante.pedidos.Patterns.factory;

import org.restaurante.pedidos.models.Category;
import org.restaurante.pedidos.models.Product;

public abstract class MenuFactory {

    public abstract Product createProduct(String name);

    // Método común para configurar categoría
    protected void setCategory(Product product, Category category) {
        product.setCategory(category);
    }
}