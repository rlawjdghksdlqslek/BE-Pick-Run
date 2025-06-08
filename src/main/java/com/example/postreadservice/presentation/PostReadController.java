package com.example.postreadservice.presentation;

import com.example.postreadservice.application.PostReadService;
import com.example.postreadservice.common.entity.BaseResponseEntity;
import com.example.postreadservice.dto.out.PostListPageResponseDto;
import com.example.postreadservice.dto.out.PostReadModelResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/post-read")
@RequiredArgsConstructor
public class PostReadController {

    private final PostReadService postReadService;

    @GetMapping("/{postUuid}")
    public BaseResponseEntity<PostReadModelResDto> getPostRead(@PathVariable String postUuid) {
        return new BaseResponseEntity<>(postReadService.getPostRead(postUuid));
    }


    @GetMapping("/popular")
    public BaseResponseEntity<PostListPageResponseDto> getPopularPosts(
            @RequestParam(required = false) Long categoryListId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "8") int size
    ) {
        return new BaseResponseEntity<>(postReadService.getPopularPosts(categoryListId, page, size));
    }
}
