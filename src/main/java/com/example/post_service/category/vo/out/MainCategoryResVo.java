package com.example.post_service.category.vo.out;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MainCategoryResVo {
    private Long id;
    private String name;

    @Builder
    public MainCategoryResVo(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
