package com.example.commentservice.domain.comment.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("comment_like")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentLike {

    @Id
    private String id;
    @Indexed
    private String commentUuid;
    private String memberUuid;

    @Builder
    public CommentLike(String id, String commentUuid, String memberUuid) {
        this.id = id;
        this.commentUuid = commentUuid;
        this.memberUuid = memberUuid;
    }
}
