package com.example.post_service.post.vo.in;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PostCreateReqVo {
    private Long mainCategoryId;
    private Long subCategoryId;
    private String title;
    private String contents;

    @Builder
    public PostCreateReqVo(
            Long mainCategoryId,
            Long subCategoryId,
            String title,
            String contents
    ) {
        this.mainCategoryId = mainCategoryId;
        this.subCategoryId = subCategoryId;
        this.title = title;
        this.contents = contents;
    }
}
