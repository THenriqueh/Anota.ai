package com.anotaai.anota.ai.mapper;

import com.anotaai.anota.ai.domain.category.Category;
import com.anotaai.anota.ai.dto.CategoryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "id", ignore = true)
    Category toEntity(CategoryDTO dto);

    CategoryDTO toDto(Category category);

}
