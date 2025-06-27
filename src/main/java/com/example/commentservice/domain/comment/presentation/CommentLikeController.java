package com.example.commentservice.domain.comment.presentation;


import com.example.commentservice.common.entity.BaseResponseEntity;
import com.example.commentservice.common.response.BaseResponseStatus;
import com.example.commentservice.domain.comment.application.CommentLikeService;
import com.example.commentservice.domain.comment.dto.in.CommentLikeCountReqDto;
import com.example.commentservice.domain.comment.dto.in.CommentLikeReqDto;
import com.example.commentservice.domain.comment.dto.out.CommentLikeCountResDto;
import com.example.commentservice.domain.comment.vo.out.CommentLikeCountResVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/comment-like")
@Tag(name = "comment")
@Slf4j
public class CommentLikeController {

    private final CommentLikeService commentLikeService;

    @Operation(
            summary = "댓글 좋아요 등록",
            description = """
            댓글 UUID와 회원 UUID를 기준으로 댓글 좋아요를 등록합니다.

            [요청 경로]
            - POST /api/v1/comment-like/{commentUuid}

            [요청 헤더]
            - X-Member-UUID: (String) 좋아요를 누른 회원 UUID

            [요청 파라미터]
            - path variable: commentUuid (String) 댓글 UUID

            [처리 로직]
            - 중복 여부 확인 후 좋아요 등록

            [예외 상황]
            - NO_EXIST_COMMENT: 댓글이 존재하지 않음
            - ALREADY_EXISTS_COMMENT_LIKE: 이미 좋아요 한 경우
        """
    )
    @PostMapping("/{commentUuid}")
    public BaseResponseEntity<Void> likeComment(
            @PathVariable String commentUuid,
            @RequestHeader("X-Member-UUID") String memberUuid
    ) {
        commentLikeService.likeComment(CommentLikeReqDto.of(commentUuid, memberUuid));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    @Operation(
            summary = "댓글 좋아요 취소",
            description = """
            댓글 UUID와 회원 UUID를 기준으로 등록된 좋아요를 취소합니다.

            [요청 경로]
            - DELETE /api/v1/comment-like/{commentUuid}

            [요청 헤더]
            - X-Member-UUID: (String) 좋아요 취소할 회원 UUID

            [요청 파라미터]
            - path variable: commentUuid (String) 댓글 UUID

            [처리 로직]
            - 해당 좋아요 정보 확인 후 삭제

            [예외 상황]
        """
    )
    @DeleteMapping("/{commentUuid}")
    public BaseResponseEntity<Void> unlikeComment(
            @PathVariable String commentUuid,
            @RequestHeader("X-Member-UUID") String memberUuid
    ) {
        commentLikeService.unlikeComment(CommentLikeReqDto.of(commentUuid, memberUuid));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    @Operation(
            summary = "댓글 좋아요 수 조회",
            description = """
            특정 댓글에 대한 총 좋아요 수를 조회합니다.

            [요청 경로]
            - GET /api/v1/comment-like/{commentUuid}

            [요청 파라미터]
            - path variable: commentUuid (String) 댓글 UUID

            [응답 필드]
            - commentUuid: commentUuid
            - likeCount: (int) 해당 댓글의 총 좋아요 수

            [처리 로직]
            - 댓글 UUID 기준으로 좋아요 수 집계
        """
    )
    @GetMapping("/{commentUuid}")
    public BaseResponseEntity<CommentLikeCountResVo> getCommentLikeCount(
            @PathVariable String commentUuid
    ) {
        CommentLikeCountResDto commentLikeCount = commentLikeService.getCommentLikeCount(
                CommentLikeCountReqDto.from(commentUuid));
        return new BaseResponseEntity<>(commentLikeCount.toVo(commentLikeCount));
    }

}
