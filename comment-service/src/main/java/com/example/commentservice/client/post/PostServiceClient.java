package com.example.commentservice.client.post;

import com.example.commentservice.client.post.dto.out.ExistsPostResDto;
import com.example.commentservice.common.entity.BaseResponseEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "post-service")
public interface PostServiceClient {

    @GetMapping("/api/v1/post/exist/{postUuid}")
    BaseResponseEntity<ExistsPostResDto> existsPost(@PathVariable String postUuid);
}
