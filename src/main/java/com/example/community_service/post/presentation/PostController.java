package com.example.community_service.post.presentation;

import com.example.community_service.post.application.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post-service/api/v1/post")
public class PostController {

    private final PostService postService;


}
