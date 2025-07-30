package com.example.post_service.kafka.event;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PostUpdatedEvent {

    private String postUuid;
    private String memberUuid;
    private Long mainCategoryId;
    private Long subCategoryId;
    private String title;
    private String contents;

    private boolean blindStatus;
    private boolean deletedStatus;

    private LocalDateTime updatedAt;

    @Builder
    public PostUpdatedEvent(
            String postUuid, String memberUuid, Long mainCategoryId, Long subCategoryId, String title, String contents,
            boolean blindStatus, boolean deletedStatus, LocalDateTime updatedAt
    ) {
        this.postUuid = postUuid;
        this.memberUuid = memberUuid;
        this.mainCategoryId = mainCategoryId;
        this.subCategoryId = subCategoryId;
        this.title = title;
        this.contents = contents;
        this.blindStatus = blindStatus;
        this.deletedStatus = deletedStatus;
        this.updatedAt = updatedAt;
    }
}
