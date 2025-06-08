package com.example.postreadservice.dto.in;

import com.example.postreadservice.entity.Image;
import com.example.postreadservice.entity.PostReadModel;
import com.example.postreadservice.kafka.event.PostCreatedEvent;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class PostReadModelReqDto {
    private String postUuid;
    private String memberUuid;
    private Long categoryListId;
    private String title;
    private String contents;
    private List<Image> images;

    private boolean blindStatus;
    private boolean deletedStatus;

    private long viewCount;
    private long likeCount;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder
    public PostReadModelReqDto(
            String postUuid,
            String memberUuid,
            Long categoryListId,
            String title,
            String contents,
            List<Image> images,
            boolean blindStatus,
            boolean deletedStatus,
            long viewCount,
            long likeCount,
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
        this.viewCount = viewCount;
        this.likeCount = likeCount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static PostReadModel from(PostCreatedEvent postCreatedEvent) {
        return PostReadModel.builder()
                .postUuid(postCreatedEvent.getPostUuid())
                .memberUuid(postCreatedEvent.getMemberUuid())
                .categoryListId(postCreatedEvent.getCategoryListId())
                .title(postCreatedEvent.getTitle())
                .contents(postCreatedEvent.getContents())
                .images(postCreatedEvent.getImages())
                .blindStatus(postCreatedEvent.isBlindStatus())
                .deletedStatus(postCreatedEvent.isDeletedStatus())
                .viewCount(0L)
                .likeCount(0L)
                .build();
    }
}
