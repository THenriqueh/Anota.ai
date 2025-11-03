package com.anotaai.anota.ai.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

public class ProductUpdateDTO {
    @NotEmpty
    private String title;
    @NotEmpty
    private String description;

    private Double price;
    @NotEmpty
    private String categoryId;

}
