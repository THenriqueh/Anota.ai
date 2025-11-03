package com.anotaai.anota.ai.exceptions;

public class CategoryNotFoundException  extends  RuntimeException{

    public CategoryNotFoundException(String id) {
        super("Categoria não encontrada com o ID: " + id);
    }

    }

