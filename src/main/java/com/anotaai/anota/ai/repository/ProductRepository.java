package com.anotaai.anota.ai.repository;

import com.anotaai.anota.ai.domain.category.Category;
import com.anotaai.anota.ai.domain.product.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {


}

