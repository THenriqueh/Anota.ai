package com.anotaai.anota.ai.exceptions;

import com.anotaai.anota.ai.domain.product.Product;

public class ProductNotFoundException extends RuntimeException  {
    public ProductNotFoundException(String id) {
        super("Produto não encontrada com o ID: " + id);
    }
}
