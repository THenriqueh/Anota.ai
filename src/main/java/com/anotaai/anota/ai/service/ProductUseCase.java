package com.anotaai.anota.ai.service;

import com.anotaai.anota.ai.domain.category.Category;
import com.anotaai.anota.ai.domain.product.Product;
import com.anotaai.anota.ai.dto.ProductDTO;
import com.anotaai.anota.ai.dto.ProductUpdateDTO;
import com.anotaai.anota.ai.exceptions.CategoryNotFoundException;
import com.anotaai.anota.ai.exceptions.ProductNotFoundException;
import com.anotaai.anota.ai.mapper.ProductMapper;
import com.anotaai.anota.ai.repository.ProductRepository;
import com.anotaai.anota.ai.service.aws.AwsSnsUseCase;
import com.anotaai.anota.ai.service.aws.MessageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductUseCase {

    private final ProductRepository productRepository;

    private final CategoryUseCase categoryUseCase;

    private final AwsSnsUseCase awsSnsUseCase;

    @Autowired
    private final ProductMapper productMapper;


    public Product createProduct(ProductDTO productData) {
        // Business logic to create a category
        Category category = this.categoryUseCase.findCategoryById(productData.categoryId()).orElseThrow(() -> new CategoryNotFoundException(productData.categoryId()));

        Product newProduct = productMapper.toEntity(productData);
        newProduct.setCategory(category);

        this.productRepository.save(newProduct);

        this.awsSnsUseCase.publishMessage(new MessageDTO(newProduct.getOwnerId()));

        return newProduct;
    }

    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    public Product updateProduct(String userId, ProductUpdateDTO productData) {
        Product productFromDb = this.productRepository.findById(userId)
                .orElseThrow(() -> new ProductNotFoundException(userId));

        this.productRepository.save(productFromDb);

        this.awsSnsUseCase.publishMessage(new MessageDTO(productFromDb.getOwnerId()));

        return productFromDb;
    }

    public void deleteProduct(String userId) {
        Product productFromDb = this.productRepository.findById(userId)
                .orElseThrow(() -> new ProductNotFoundException(userId));

        this.productRepository.delete(productFromDb);
    }
}
