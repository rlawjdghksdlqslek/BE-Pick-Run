package com.example.commentservice.client.post.dto.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ExistsPostResDto {

    private boolean existsPost;

    @Builder
    public ExistsPostResDto(boolean existsPost) {
        this.existsPost = existsPost;
    }

    public static ExistsPostResDto from(boolean existsPost) {
        return ExistsPostResDto.builder()
                .existsPost(existsPost)
                .build();
    }
}
