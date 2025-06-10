package com.example.commentservice.domain.dto.in;

import com.example.commentservice.domain.entity.Comment;
import com.example.commentservice.domain.vo.in.CommentCreateReqVo;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CommentCreateReqDto {

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
