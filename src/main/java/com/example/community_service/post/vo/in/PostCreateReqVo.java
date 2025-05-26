package com.example.community_service.post.vo.in;

import com.example.community_service.post.entity.Image;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class PostCreateReqVo {
    private Long categoryListId;
    private String title;
    private String contents;
    private List<Image> images;

    @Builder
    public PostCreateReqVo(
            Long categoryListId,
            String title,
            String contents,
            List<Image> images
    ) {
        this.categoryListId = categoryListId;
        this.title = title;
        this.contents = contents;
        this.images = images;
    }
}
