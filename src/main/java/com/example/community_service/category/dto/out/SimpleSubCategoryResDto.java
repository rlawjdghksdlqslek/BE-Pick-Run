package com.example.community_service.category.dto.out;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SimpleSubCategoryResDto {

    private Long id;
    private String name;
    private String color;

    @Builder
    public SimpleSubCategoryResDto(Long id, String name, String color) {
        this.id = id;
        this.name = name;
        this.color = color;
    }
}
