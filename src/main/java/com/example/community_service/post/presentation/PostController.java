package com.example.community_service.post.presentation;

import com.example.community_service.common.response.BaseResponseStatus;
import com.example.community_service.common.entity.BaseResponseEntity;
import com.example.community_service.post.application.PostService;
import com.example.community_service.post.dto.in.PostCreateReqDto;
import com.example.community_service.post.dto.in.PostUpdateReqDto;
import com.example.community_service.post.vo.in.PostCreateReqVo;
import com.example.community_service.post.vo.in.PostUpdateReqVo;
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
}
