package com.example.community_service.post.dto.in;

import com.example.community_service.post.entity.Image;
import com.example.community_service.post.vo.in.PostUpdateReqVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@Builder
public class PostUpdateReqDto {
    private String title;
    private String contents;
    private Long categoryListId;
    private List<Image> images;


    @Builder
    public PostUpdateReqDto(
            String title,
            String contents,
            Long categoryListId,
            List<Image> images) {
        this.title = title;
        this.contents = contents;
        this.categoryListId = categoryListId;
        this.images = images;
    }

    public static PostUpdateReqDto from(PostUpdateReqVo postUpdateReqVo) {
        return PostUpdateReqDto.builder()
                .title(postUpdateReqVo.getTitle())
                .contents(postUpdateReqVo.getContents())
                .categoryListId(postUpdateReqVo.getCategoryListId())
                .images(postUpdateReqVo.getImages())
                .build();

    }
}