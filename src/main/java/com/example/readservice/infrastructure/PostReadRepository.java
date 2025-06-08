package com.example.readservice.infrastructure;

import com.example.readservice.entity.PostReadModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface PostReadRepository extends MongoRepository<PostReadModel,String> {
    Optional<PostReadModel> findByPostUuid(String id);
}
