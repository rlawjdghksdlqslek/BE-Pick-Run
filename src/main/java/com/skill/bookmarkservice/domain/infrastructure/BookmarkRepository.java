package com.skill.bookmarkservice.domain.infrastructure;

import com.skill.bookmarkservice.domain.entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    Optional<Bookmark> findByMemberUuidAndPostUuid(String memberUuid, String postUuid);
}
