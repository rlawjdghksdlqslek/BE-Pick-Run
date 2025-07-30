package com.example.post_service.category.vo.in;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CategoryListReqVo {

    private Long mainCategoryId;
    private Long subCategoryId;

    @Builder
    public CategoryListReqVo(
            Long mainCategoryId,
            Long subCategoryId
    ) {
        this.mainCategoryId = mainCategoryId;
        this.subCategoryId = subCategoryId;
    }
}
