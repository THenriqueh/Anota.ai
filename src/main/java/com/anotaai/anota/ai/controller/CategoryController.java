package com.anotaai.anota.ai.controller;

import com.anotaai.anota.ai.domain.category.Category;
import com.anotaai.anota.ai.dto.CategoryDTO;
import com.anotaai.anota.ai.dto.CategoryUpdateDTO;
import com.anotaai.anota.ai.service.CategoryUseCase;
import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private CategoryUseCase categoryUseCase;

    public CategoryController(CategoryUseCase categoryUseCase) {
        this.categoryUseCase = categoryUseCase;
    }

    @PostMapping
    public ResponseEntity<Category> insertCategory(@RequestBody CategoryDTO category) {
        Category newCategory = this.categoryUseCase.createCategory(category);
        return ResponseEntity.ok().body(newCategory);
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = this.categoryUseCase.getAllCategories();
        return ResponseEntity.ok().body(categories);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<Category> updateCategory(@PathVariable("categoryId") String userId, @RequestBody CategoryUpdateDTO category) {
        Category updatedCategory = this.categoryUseCase.updateCategory(userId, category);
        return ResponseEntity.ok().body(updatedCategory);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("categoryId") String userId) {
        this.categoryUseCase.deleteCategory(userId);
        return ResponseEntity.noContent().build();

    }
}
