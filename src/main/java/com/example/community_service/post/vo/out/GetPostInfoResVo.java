package com.example.community_service.post.vo.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetPostInfoResVo {

    private String memberUuid;
    private String postUuid;
    private String postTitle;

    @Builder
    public GetPostInfoResVo(String memberUuid, String postUuid, String postTitle) {
        this.memberUuid = memberUuid;
        this.postUuid = postUuid;
        this.postTitle = postTitle;
    }
}
