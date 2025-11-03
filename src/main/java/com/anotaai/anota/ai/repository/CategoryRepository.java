package com.anotaai.anota.ai.repository;

import com.anotaai.anota.ai.domain.category.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public interface CategoryRepository extends MongoRepository<Category, String> {
}
