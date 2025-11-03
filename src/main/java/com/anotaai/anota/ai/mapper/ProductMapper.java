package com.anotaai.anota.ai.mapper;

import com.anotaai.anota.ai.domain.product.Product;
import com.anotaai.anota.ai.dto.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "id", ignore = true)
    Product toEntity(ProductDTO dto);

    ProductDTO toDto(Product category);
}
