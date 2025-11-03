package com.anotaai.anota.ai.service;

import com.anotaai.anota.ai.domain.category.Category;
import com.anotaai.anota.ai.domain.product.Product;
import com.anotaai.anota.ai.dto.ProductDTO;
import com.anotaai.anota.ai.exceptions.CategoryNotFoundException;
import com.anotaai.anota.ai.exceptions.ProductNotFoundException;
import com.anotaai.anota.ai.mapper.ProductMapper;
import com.anotaai.anota.ai.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductUseCase {

    private ProductRepository productRepository;

    private CategoryUseCase categoryUseCase;

    @Autowired
    private ProductMapper productMapper;

    public ProductUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(ProductDTO productData) {
        // Business logic to create a category
        Category category = this.categoryUseCase.findCategoryById(productData.categoryId()).orElseThrow(() -> new CategoryNotFoundException(productData.categoryId()));

        Product newProduct = productMapper.toEntity(productData);
        newProduct.setCategory(category);
        this.productRepository.save(newProduct);
        return newProduct;
    }

    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    public Product updateProduct(String userId, ProductDTO productData) {
        Product productFromDb = this.productRepository.findById(userId)
                .orElseThrow(() -> new ProductNotFoundException(userId));

        if(!productData.title().isEmpty()) productFromDb.setTitle(productData.title());
        if(!productData.description().isEmpty()) productFromDb.setDescription(productData.description());
        if(productData.price() != null) productFromDb.setPrice(productData.price());
        if(!productData.categoryId().isEmpty()) {
            Category category = this.categoryUseCase.findCategoryById(productData.categoryId()).orElseThrow(() -> new CategoryNotFoundException(productData.categoryId()));
            productFromDb.setCategory(category);
        }

        this.productRepository.save(productFromDb);

        return productFromDb;
    }

    public void deleteProduct(String userId) {
        Product productFromDb = this.productRepository.findById(userId)
                .orElseThrow(() -> new ProductNotFoundException(userId));

        this.productRepository.delete(productFromDb);
    }
}
