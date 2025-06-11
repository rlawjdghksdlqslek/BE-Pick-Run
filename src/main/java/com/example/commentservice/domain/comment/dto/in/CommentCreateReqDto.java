package com.example.commentservice.domain.comment.dto.in;

import com.example.commentservice.domain.comment.entity.Comment;
import com.example.commentservice.domain.comment.vo.in.CommentCreateReqVo;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CommentCreateReqDto {

    private String commentUuid;
    private String postUuid;
    private String memberUuid;
    private String content;

    @Builder
    public CommentCreateReqDto(
            String postUuid,
            String memberUuid,
            String content
    ) {
        this.postUuid = postUuid;
        this.memberUuid = memberUuid;
        this.content = content;
    }

    public Comment toEntity() {
        return Comment.builder()
                .commentUuid(UUID.randomUUID().toString())
                .postUuid(this.postUuid)
                .memberUuid(this.memberUuid)
                .content(this.content)
                .blind_status(false)
                .deleted_status(false)
                .deleted_at(null)
                .build();
    }

    public static CommentCreateReqDto of(
            String postUuid,
            String memberUuid,
            CommentCreateReqVo commentCreateReqVo
    ) {
        return CommentCreateReqDto.builder()
                .postUuid(postUuid)
                .memberUuid(memberUuid)
                .content(commentCreateReqVo.getContent())
                .build();
    }
}
