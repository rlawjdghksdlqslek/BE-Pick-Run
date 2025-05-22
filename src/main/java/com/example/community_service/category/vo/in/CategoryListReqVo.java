package com.example.community_service.category.vo.in;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CategoryListReqVo {

    private Integer mainCategoryId;
    private Integer subCategoryId;

    @Builder
    public CategoryListReqVo(
            Integer mainCategoryId,
            Integer subCategoryId
    ) {
        this.mainCategoryId = mainCategoryId;
        this.subCategoryId = subCategoryId;
    }
}
