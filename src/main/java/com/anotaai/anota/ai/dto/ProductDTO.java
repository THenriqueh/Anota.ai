package com.anotaai.anota.ai.dto;

public record ProductDTO(String id,
                         String title,
                         String description,
                         String ownerId,
                         Integer price,
                         String categoryId) {
}
