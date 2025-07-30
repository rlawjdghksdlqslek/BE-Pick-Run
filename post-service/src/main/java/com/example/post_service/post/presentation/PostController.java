package com.example.post_service.post.presentation;

import com.example.post_service.common.entity.BaseResponseEntity;
import com.example.post_service.common.response.BaseResponseStatus;
import com.example.post_service.post.application.PostService;
import com.example.post_service.post.dto.in.PostCreateReqDto;
import com.example.post_service.post.dto.in.PostUpdateReqDto;
import com.example.post_service.post.dto.out.ExistsPostDto;
import com.example.post_service.post.vo.in.PostCreateReqVo;
import com.example.post_service.post.vo.in.PostUpdateReqVo;
import com.example.post_service.post.vo.out.GetPostInfoResVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/post")
@Tag(name = "post")
@Slf4j
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
     *
     * @param memberUuid
     * @param postUuid
     * @param postUpdateReqVo
     * @return
     */
    @Operation(summary = "질문 수정")
    @PatchMapping("/{postUuid}")
    public BaseResponseEntity<Void> updatePost(
            @RequestHeader("X-Member-UUID") String memberUuid,
            @PathVariable String postUuid,
            @RequestBody PostUpdateReqVo postUpdateReqVo
    ) {
        postService.updatePost(memberUuid, postUuid, PostUpdateReqDto.from(postUpdateReqVo));
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

    /**
     * 3. 질문 정보 조회
     *
     * @param postUuid
     * @return
     */
    @Operation(summary = "질문 정보 조회")
    @GetMapping("/{postUuid}")
    public BaseResponseEntity<GetPostInfoResVo> getPostInfo(@PathVariable String postUuid) {
        return new BaseResponseEntity<>(postService.getPostInfo(postUuid).toVo());
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
                    """
    )
    @GetMapping("/exist/{postUuid}")
    public BaseResponseEntity<ExistsPostDto> existsPost(
            @PathVariable String postUuid
    ) {
        return new BaseResponseEntity<>(postService.existsPost(postUuid));
    }

    @DeleteMapping("{postUuid}")
    public BaseResponseEntity<Void> deletePost(
            @RequestHeader("X-Member-UUID") String memberUuid,
            @PathVariable String postUuid
    ) {
        postService.softDeletePost(memberUuid, postUuid);
        return new BaseResponseEntity<>(BaseResponseStatus.SUCCESS);
    }

}
