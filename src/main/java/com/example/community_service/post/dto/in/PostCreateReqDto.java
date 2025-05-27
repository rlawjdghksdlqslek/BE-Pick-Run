package com.example.community_service.post.dto.in;

import com.example.community_service.post.entity.Image;
import com.example.community_service.post.entity.Post;
import com.example.community_service.post.vo.in.PostCreateReqVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class PostCreateReqDto {

    private String postUuid;
    private String memberUuid;
    private Long categoryListId;
    private String title;
    private String contents;
    private List<Image> images;

    private boolean blindStatus;
    private boolean deletedStatus;
    private LocalDateTime deletedAt;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder
    public PostCreateReqDto(
            String postUuid,
            String memberUuid,
            Long categoryListId,
            String title,
            String contents,
            List<Image> images,
            boolean blindStatus,
            boolean deletedStatus,
            LocalDateTime deletedAt,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        this.postUuid = postUuid;
        this.memberUuid = memberUuid;
        this.categoryListId = categoryListId;
        this.title = title;
        this.contents = contents;
        this.images = images;
        this.blindStatus = blindStatus;
        this.deletedStatus = deletedStatus;
        this.deletedAt = deletedAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static PostCreateReqDto of(String memberUuid, PostCreateReqVo postCreateReqVo) {
        return PostCreateReqDto.builder()
                .postUuid(UUID.randomUUID().toString())
                .memberUuid(memberUuid)
                .categoryListId(postCreateReqVo.getCategoryListId())
                .title(postCreateReqVo.getTitle())
                .contents(postCreateReqVo.getContents())
                .images(postCreateReqVo.getImages())
                .deletedAt(null)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public Post toEntity() {
        return Post.builder()
                .postUuid(this.postUuid)
                .memberUuid(this.memberUuid)
                .categoryListId(this.categoryListId)
                .title(this.title)
                .contents(this.contents)
                .images(this.images)
                .blindStatus(false)
                .deletedStatus(false)
                .deletedAt(null)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

}
