package com.example.postreadservice.infrastructure;

import com.example.postreadservice.entity.PostReadModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface PostReadRepository extends MongoRepository<PostReadModel,String> {
    Optional<PostReadModel> findByPostUuid(String id);
}
