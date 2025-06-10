package com.example.post_service.post.presentation;

import com.example.post_service.common.entity.BaseResponseEntity;
import com.example.post_service.common.response.BaseResponseStatus;
import com.example.post_service.post.application.PostService;
import com.example.post_service.post.dto.in.PostCreateReqDto;
import com.example.post_service.post.dto.in.PostUpdateReqDto;
import com.example.post_service.post.dto.out.ExistsPostDto;
import com.example.post_service.post.vo.in.PostCreateReqVo;
import com.example.post_service.post.vo.in.PostUpdateReqVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/post")
@Tag(name = "post")
public class PostController {

    private final PostService postService;

    /**
     * 1. 질문 등록
     *
     * @param memberUuid
     * @param postCreateReqVo
     * @return
     */
    @Operation(summary = "질문 등록")
    @PostMapping("/create")
    public BaseResponseEntity<Void> createPost(
            @RequestHeader("X-Member-UUID") String memberUuid,
            @RequestBody PostCreateReqVo postCreateReqVo
    ) {
        postService.createPost(PostCreateReqDto.of(memberUuid, postCreateReqVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    /**
     * 2. 질문 수정
     * @param memberUuid
     * @param postId
     * @param postUpdateReqVo
     * @return
     */
    @Operation(summary = "질문 수정")
    @PatchMapping("/{postId}")
    public BaseResponseEntity<Void> updatePost(
            @RequestHeader("X-Member-UUID") String memberUuid,
            @PathVariable String postId,
            @RequestBody PostUpdateReqVo postUpdateReqVo
    ) {
        postService.updatePost(memberUuid, postId, PostUpdateReqDto.from(postUpdateReqVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    @Operation(
            summary = "게시글 존재 여부 확인",
            description = """
            게시글 UUID를 통해 해당 게시글이 존재하는지 확인합니다.

            [요청 경로]
            - /api/v1/post/exist/{postUuid}

            [요청 파라미터]
            - path variable: postUuid (String) 게시글 고유 식별자

            [응답 필드]
            - exists: (boolean) 해당 게시글이 존재하면 true, 없으면 false

            [처리 로직]
            - UUID로 게시글을 조회하고 존재 여부만 반환
            - 삭제 상태, 블라인드 상태 포함 여부는 도메인 로직에 따라 처리

            [예외 상황]
            - UUID 형식이 잘못된 경우 400 Bad Request
            """
    )
    @GetMapping("/exist/{postUuid}")
    public BaseResponseEntity<ExistsPostDto> existPost(
            @PathVariable String postUuid
    ){
        return new BaseResponseEntity<>(postService.existsPost(postUuid));
    }

}
