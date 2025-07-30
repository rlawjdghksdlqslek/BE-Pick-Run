package com.example.commentservice.domain.comment.presentation;

import com.example.commentservice.common.entity.BaseResponseEntity;
import com.example.commentservice.common.response.BaseResponseStatus;
import com.example.commentservice.domain.comment.application.CommentService;
import com.example.commentservice.domain.comment.dto.in.CommentCreateReqDto;
import com.example.commentservice.domain.comment.dto.in.CommentDeleteReqDto;
import com.example.commentservice.domain.comment.dto.in.CommentUpdateReqDto;
import com.example.commentservice.domain.comment.dto.out.CommentListPageResDto;
import com.example.commentservice.domain.comment.dto.out.CommentResDto;
import com.example.commentservice.domain.comment.entity.CommentSortType;
import com.example.commentservice.domain.comment.vo.in.CommentCreateReqVo;
import com.example.commentservice.domain.comment.vo.in.CommentUpdateReqVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/comment")
@Tag(name = "comment")
@Slf4j
public class CommentController {

    private final CommentService commentService;

    @Operation(
            summary = "댓글 생성",
            description = """
                    지정된 게시글에 대해 회원이 댓글을 작성합니다.
                    
                    [요청 경로]
                    - /api/v1/comment/{postUuid}
                    
                    [요청 헤더]
                    - X-Member-UUID: (String) 댓글 작성자 고유 식별자 (필수)
                    
                    [요청 파라미터]
                    - path variable: postUuid (String) 대상 게시글의 UUID
                    
                    [요청 바디]
                    - contents: (String) 댓글 내용 (필수)
                    
                    [응답]
                    - 성공 시: 200 OK + SUCCESS 메시지
                    
                    [처리 로직]
                    - 게시글 존재 여부 확인
                    - 댓글 등록 처리 및 저장
                    
                    [예외 상황]
                    - NO_EXIST_POST: 게시글이 존재하지 않는 경우
                    """
    )
    @PostMapping("/post/{postUuid}")
    public BaseResponseEntity<Void> createComment(
            @RequestHeader("X-Member-UUID") String memberUuid,
            @PathVariable String postUuid,
            @RequestBody CommentCreateReqVo commentCreateReqVo
    ) {
        commentService.createComment(CommentCreateReqDto.of(postUuid, memberUuid, commentCreateReqVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    @Operation(
            summary = "댓글 수정",
            description = """
            댓글 UUID를 기준으로 댓글 내용을 수정합니다.

            [요청 경로]
            - /api/v1/comment/{commentUuid}

            [요청 헤더]
            - X-Member-UUID: (String) 댓글 작성자 UUID

            [요청 바디]
            - contents: (String) 수정할 댓글 내용

            [처리 로직]
            - 댓글 UUID 및 작성자 UUID 확인
            - 댓글 내용 업데이트 처리

            [예외 상황]
            - NO_EXIST_COMMENT: 댓글이 존재하지 않는 경우
            - UNAUTHORIZED_MEMBER: 수정 권한 없음
        """
    )
    @PatchMapping("/{commentUuid}")
    public BaseResponseEntity<Void> updateComment(
            @PathVariable String commentUuid,
            @RequestHeader("X-Member-UUID") String memberUuid,
            @RequestBody CommentUpdateReqVo commentUpdateReqVo
    ) {
        commentService.updateComment(CommentUpdateReqDto.of(commentUuid, memberUuid, commentUpdateReqVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    @Operation(
            summary = "댓글 삭제",
            description = """
            댓글 UUID를 통해 댓글을 삭제합니다.

            [요청 경로]
            - /api/v1/comment/{commentUuid}

            [요청 헤더]
            - X-Member-UUID: (String) 댓글 작성자 UUID

            [처리 로직]
            - 댓글 UUID 및 작성자 UUID 확인
            - 댓글 삭제 처리

            [예외 상황]
            - NO_EXIST_COMMENT: 댓글이 존재하지 않는 경우
            - UNAUTHORIZED_MEMBER: 삭제 권한 없음
        """
    )
    @DeleteMapping("/{commentUuid}")
    public BaseResponseEntity<Void> deleteComment(
            @PathVariable String commentUuid,
            @RequestHeader("X-Member-UUID") String memberUuid
    ) {
        commentService.deleteComment(CommentDeleteReqDto.of(commentUuid, memberUuid));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    @Operation(
            summary = "게시글 댓글 목록 조회",
            description = """
            게시글 UUID를 기반으로 해당 게시글에 등록된 댓글 목록을 정렬 기준에 따라 조회합니다.

            [요청 경로]
            - /api/v1/comment/post/{postUuid}/list

            [요청 파라미터]
            - postUuid: (String) 게시글 UUID
            - page: (int) 페이지 번호 (기본값: 0)
            - commentSortType: (String) 정렬 기준 (RECENT, POPULAR)

            [처리 로직]
            - 게시글 UUID에 따른 댓글 페이징 목록 조회

            [예외 상황]
            - NO_EXIST_POST: 게시글이 존재하지 않음
        """
    )
    @GetMapping("/post/{postUuid}/list")
    public BaseResponseEntity<CommentListPageResDto> getCommentsByPostUuid(
            @PathVariable String postUuid,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "RECENT") CommentSortType commentSortType
    ) {
        return new BaseResponseEntity<>(commentService.getCommentsByPostUuid(postUuid, page, commentSortType));
    }

    @Operation(
            summary = "단일 댓글 상세 조회",
            description = """
            댓글 UUID를 통해 단일 댓글의 상세 정보를 조회합니다.

            [요청 경로]
            - /api/v1/comment/{commentUuid}

            [요청 파라미터]
            - commentUuid: (String) 댓글 UUID

            [처리 로직]
            - 댓글 UUID 기준으로 댓글 상세 정보 반환

            [예외 상황]
            - NO_EXIST_COMMENT: 댓글이 존재하지 않음
        """
    )
    @GetMapping("/{commentUuid}")
    public BaseResponseEntity<CommentResDto> getCommentByCommentUuid(@PathVariable String commentUuid) {
        return new BaseResponseEntity<>(commentService.getCommentByCommentUuid(commentUuid));
    }
}
