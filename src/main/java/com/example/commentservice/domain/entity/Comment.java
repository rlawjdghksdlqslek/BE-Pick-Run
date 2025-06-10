package com.example.commentservice.domain.entity;

import com.example.commentservice.common.entity.BaseDocument;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;


@Document("comment")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseDocument {

    @Id
    private String id;
    private String commentUuid;
    private String postUuid;
    private String memberUuid;
    private String content;
    private boolean blind_status;
    private boolean deleted_status;
    private LocalDateTime deleted_at;


    @Builder
    public Comment(
            String commentUuid,
            String postUuid,
            String memberUuid,
            String content,
            boolean blind_status,
            boolean deleted_status,
            LocalDateTime deleted_at
    ) {
        this.commentUuid = commentUuid;
        this.postUuid = postUuid;
        this.memberUuid = memberUuid;
        this.content = content;
        this.blind_status = blind_status;
        this.deleted_status = deleted_status;
        this.deleted_at = deleted_at;
    }
}
