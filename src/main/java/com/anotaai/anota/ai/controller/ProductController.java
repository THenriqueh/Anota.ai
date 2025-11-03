package com.anotaai.anota.ai.controller;

import com.anotaai.anota.ai.domain.product.Product;
import com.anotaai.anota.ai.dto.ProductDTO;
import com.anotaai.anota.ai.service.ProductUseCase;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductUseCase productUseCase;

    @PostMapping
    public ResponseEntity<Product> insertProduct(@RequestBody ProductDTO product) {
        Product newProduct = this.productUseCase.createProduct(product);
        return ResponseEntity.ok().body(newProduct);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> categories = this.productUseCase.getAllProducts();
        return ResponseEntity.ok().body(categories);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Product> updateProduct(@PathParam("id") String userId, @RequestBody ProductDTO product) {
        Product updatedProduct = this.productUseCase.updateProduct(userId, product);
        return ResponseEntity.ok().body(updatedProduct);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteProduct(@PathParam("id") String userId) {
        this.productUseCase.deleteProduct(userId);
        return ResponseEntity.noContent().build();

    }
}
