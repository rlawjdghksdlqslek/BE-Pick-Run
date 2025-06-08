package com.example.community_service.kafka.event;

import com.example.community_service.post.entity.Image;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class PostCreatedEvent {

    private String postUuid;
    private String memberUuid;
    private Long categoryListId;
    private String title;
    private String contents;
    private List<Image> images;

    private boolean blindStatus;
    private boolean deletedStatus;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder
    public PostCreatedEvent(
            String postUuid,
            String memberUuid,
            Long categoryListId,
            String title,
            String contents,
            List<Image> images,
            boolean blindStatus,
            boolean deletedStatus,
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
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
