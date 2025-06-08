package com.example.postreadservice.infrastructure;


import com.example.postreadservice.entity.PostReadModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostReadCustomRepository {
    Page<PostReadModel> findByCategoryListId(Long categoryListId, Pageable pageable);
}
