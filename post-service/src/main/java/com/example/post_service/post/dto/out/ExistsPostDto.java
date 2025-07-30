package com.example.post_service.post.dto.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ExistsPostDto {

    private boolean existsPost;

    @Builder
    public ExistsPostDto(boolean existsPost) {
        this.existsPost = existsPost;
    }

    public static ExistsPostDto from(boolean existsPost) {
        return ExistsPostDto.builder()
                .existsPost(existsPost)
                .build();
    }
}
