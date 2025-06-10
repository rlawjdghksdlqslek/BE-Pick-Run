package com.example.commentservice.domain.presentation;

import com.example.commentservice.common.entity.BaseResponseEntity;
import com.example.commentservice.common.response.BaseResponseStatus;
import com.example.commentservice.domain.application.CommentService;
import com.example.commentservice.domain.dto.in.CommentCreateReqDto;
import com.example.commentservice.domain.dto.in.CommentUpdateReqDto;
import com.example.commentservice.domain.vo.in.CommentCreateReqVo;
import com.example.commentservice.domain.vo.in.CommentUpdateReqVo;
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
    @PostMapping("/{postUuid}")
    public BaseResponseEntity<Void> createComment(
            @RequestHeader("X-Member-UUID") String memberUuid,
            @PathVariable String postUuid,
            @RequestBody CommentCreateReqVo commentCreateReqVo
    ) {
        commentService.createComment(CommentCreateReqDto.of(postUuid, memberUuid, commentCreateReqVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    @PatchMapping("/{commentUuid}")
    public BaseResponseEntity<Void> updateComment(
            @PathVariable String commentUuid,
            @RequestHeader("X-Member-UUID") String memberUuid,
            @RequestBody CommentUpdateReqVo commentUpdateReqVo
    ) {
        commentService.updateComment(CommentUpdateReqDto.of(commentUuid, memberUuid, commentUpdateReqVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }
}
