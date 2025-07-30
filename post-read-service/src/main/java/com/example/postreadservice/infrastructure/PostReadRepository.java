package com.example.postreadservice.infrastructure;

import com.example.postreadservice.entity.PostReadModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface PostReadRepository extends MongoRepository<PostReadModel, String>, PostReadCustomRepository {
    Optional<PostReadModel> findByPostUuid(String postUuid);

    Optional<PostReadModel> findByPostUuidAndDeletedStatusIsFalse(String postUuid);

    Page<PostReadModel> findByTitleContainingIgnoreCaseAndDeletedStatusIsFalse(String titleKeyword, Pageable pageable);
}
