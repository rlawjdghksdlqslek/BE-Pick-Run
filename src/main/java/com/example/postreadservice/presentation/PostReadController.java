package com.example.postreadservice.presentation;

import com.example.postreadservice.application.PostReadService;
import com.example.postreadservice.common.entity.BaseResponseEntity;
import com.example.postreadservice.dto.out.PostReadModelResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/post-read")
@RequiredArgsConstructor
public class PostReadController {

    private final PostReadService postReadService;

    @GetMapping("/{postUuid}")
    public BaseResponseEntity<PostReadModelResDto> getPostRead(@PathVariable String postUuid) {
        return new BaseResponseEntity<>(postReadService.getPostRead(postUuid));
    }


}
