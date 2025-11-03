package com.anotaai.anota.ai.dto;

import jakarta.validation.constraints.NotEmpty;

public class CategoryUpdateDTO {
    @NotEmpty
    private String title;
    @NotEmpty
    private String description;
    @NotEmpty
    private String ownerId;
}