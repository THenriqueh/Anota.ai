package com.anotaai.anota.ai.controller;

import com.anotaai.anota.ai.domain.category.Category;
import com.anotaai.anota.ai.dto.CategoryDTO;
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

    @PutMapping("/{userId}")
    public ResponseEntity<Category> updateCategory(@PathParam("id") String userId, @RequestBody CategoryDTO category) {
        Category updatedCategory = this.categoryUseCase.updateCategory(userId, category);
        return ResponseEntity.ok().body(updatedCategory);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteCategory(@PathParam("id") String userId) {
        this.categoryUseCase.deleteCategory(userId);
        return ResponseEntity.noContent().build();

    }
}
