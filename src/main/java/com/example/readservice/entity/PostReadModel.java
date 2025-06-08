package com.example.readservice.entity;

import com.example.readservice.common.entity.BaseDocument;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "post_read")
@Getter
@NoArgsConstructor
public class PostReadModel extends BaseDocument {

    @Id
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

    @Builder
    public PostReadModel(
            String postUuid,
            String memberUuid,
            Long categoryListId,
            String title,
            String contents,
            List<Image> images,
            boolean blindStatus,
            boolean deletedStatus,
            long viewCount,
            long likeCount
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
    }
}
