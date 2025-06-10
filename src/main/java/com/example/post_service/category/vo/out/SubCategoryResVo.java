package com.example.post_service.category.vo.out;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SubCategoryResVo {
    private Long id;
    private String name;
    private String color;

    @Builder
    public SubCategoryResVo(Long id, String name, String color) {
        this.id = id;
        this.name = name;
        this.color = color;
    }
}
