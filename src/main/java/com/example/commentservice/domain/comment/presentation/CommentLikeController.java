package com.example.commentservice.domain.comment.presentation;


import com.example.commentservice.common.entity.BaseResponseEntity;
import com.example.commentservice.common.response.BaseResponseStatus;
import com.example.commentservice.domain.comment.application.CommentLikeService;
import com.example.commentservice.domain.comment.dto.in.CommentLikeReqDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/comment")
@Tag(name = "comment")
@Slf4j
public class CommentLikeController {

    private final CommentLikeService commentLikeService;

    @PostMapping("/{commentUuid}")
    public BaseResponseEntity<Void> likeComment(
            @PathVariable String commentUuid,
            @RequestHeader("X-Member-UUID") String memberUuid
    ) {
        commentLikeService.likeComment(CommentLikeReqDto.of(commentUuid, memberUuid));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }
}
