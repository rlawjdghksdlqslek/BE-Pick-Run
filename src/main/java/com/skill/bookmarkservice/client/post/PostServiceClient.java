package com.skill.bookmarkservice.client.post;


import com.skill.bookmarkservice.client.post.dto.out.ExistsPostResDto;
import com.skill.bookmarkservice.common.entity.BaseResponseEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "post-service")
public interface PostServiceClient {

    @GetMapping("/api/v1/post/exist/{postUuid}")
    BaseResponseEntity<ExistsPostResDto> existsPost(@PathVariable String postUuid);
}
