package com.example.post_service.post.vo.in;

import com.example.post_service.post.entity.Image;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class PostCreateReqVo {
    private Long mainCategoryId;
    private Long subCategoryId;
    private String title;
    private String contents;
    private List<Image> images;

    @Builder
    public PostCreateReqVo(
            Long mainCategoryId,
            Long subCategoryId,
            String title,
            String contents,
            List<Image> images
    ) {
        this.mainCategoryId = mainCategoryId;
        this.subCategoryId = subCategoryId;
        this.title = title;
        this.contents = contents;
        this.images = images;
    }
}
