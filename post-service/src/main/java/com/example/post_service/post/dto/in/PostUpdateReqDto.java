package com.example.post_service.post.dto.in;

import com.example.post_service.post.vo.in.PostUpdateReqVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
public class PostUpdateReqDto {
    private String title;
    private String contents;
    private Long mainCategoryId;
    private Long subCategoryId;


    @Builder
    public PostUpdateReqDto(
            String title,
            String contents,
            Long mainCategoryId,
            Long subCategoryId
    ) {
        this.title = title;
        this.contents = contents;
        this.mainCategoryId = mainCategoryId;
        this.subCategoryId = subCategoryId;
    }

    public static PostUpdateReqDto from(PostUpdateReqVo postUpdateReqVo) {
        return PostUpdateReqDto.builder()
                .title(postUpdateReqVo.getTitle())
                .contents(postUpdateReqVo.getContents())
                .mainCategoryId(postUpdateReqVo.getMainCategoryId())
                .subCategoryId(postUpdateReqVo.getSubCategoryId())
                .build();

    }
}