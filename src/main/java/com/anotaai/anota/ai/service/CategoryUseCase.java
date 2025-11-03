package com.anotaai.anota.ai.service;

import com.anotaai.anota.ai.domain.category.Category;
import com.anotaai.anota.ai.dto.CategoryDTO;
import com.anotaai.anota.ai.dto.CategoryUpdateDTO;
import com.anotaai.anota.ai.exceptions.CategoryNotFoundException;
import com.anotaai.anota.ai.mapper.CategoryMapper;
import com.anotaai.anota.ai.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryUseCase {

    private final CategoryRepository categoryRepository;

    @Autowired
    private final CategoryMapper categoryMapper;

    public Category createCategory(CategoryDTO categoryData) {
        // Business logic to create a category
        Category newCategory = categoryMapper.toEntity(categoryData);
        this.categoryRepository.save(newCategory);
        return newCategory;
    }

    public List<Category> getAllCategories() {
        return this.categoryRepository.findAll();
    }

    public Optional<Category> findCategoryById(String categoryId) {
        return Optional.ofNullable(this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId)));
    }

    public Category updateCategory(String userId, CategoryUpdateDTO categoryData) {
        Category categoryFromDb = this.categoryRepository.findById(userId)
                .orElseThrow(() -> new CategoryNotFoundException(userId));

        this.categoryRepository.save(categoryFromDb);

        return categoryFromDb;
    }

    public void deleteCategory(String userId) {
        Category categoryFromDb = this.categoryRepository.findById(userId)
                .orElseThrow(() -> new CategoryNotFoundException(userId));

        this.categoryRepository.delete(categoryFromDb);
    }

}
