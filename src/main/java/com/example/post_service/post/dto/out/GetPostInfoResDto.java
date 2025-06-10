package com.example.post_service.post.dto.out;

import com.example.post_service.post.entity.Post;
import com.example.post_service.post.vo.out.GetPostInfoResVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetPostInfoResDto {

    private String memberUuid;
    private String postUuid;
    private String postTitle;

    @Builder
    public GetPostInfoResDto(String memberUuid, String postUuid, String postTitle) {
        this.memberUuid = memberUuid;
        this.postUuid = postUuid;
        this.postTitle = postTitle;
    }

    public static GetPostInfoResDto from(Post post) {
        return GetPostInfoResDto.builder()
                .memberUuid(post.getMemberUuid())
                .postUuid(post.getPostUuid())
                .postTitle(post.getTitle())
                .build();
    }

    public GetPostInfoResVo toVo() {
        return GetPostInfoResVo.builder()
                .memberUuid(memberUuid)
                .postUuid(postUuid)
                .postTitle(postTitle)
                .build();
    }
}
