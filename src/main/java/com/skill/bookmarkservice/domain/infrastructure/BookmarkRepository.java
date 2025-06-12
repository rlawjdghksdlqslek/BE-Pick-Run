package com.skill.bookmarkservice.domain.infrastructure;

import com.skill.bookmarkservice.domain.entity.Bookmark;
import feign.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Long>, BookmarkCustomRepository {
    Optional<Bookmark> findByMemberUuidAndPostUuid(String memberUuid, String postUuid);

    boolean existsByMemberUuidAndPostUuid(String memberUuid, String postUuid);

    void  deleteByMemberUuidAndPostUuid(String memberUuid, String postUuid);

    @Query("SELECT b FROM Bookmark b WHERE b.memberUuid = :memberUuid AND b.isBookmarked = true")
    Page<Bookmark> findBookmarkedPosts(String memberUuid, Pageable pageable);

    @Query("SELECT b.postUuid FROM Bookmark b WHERE b.memberUuid = :memberUuid AND b.isBookmarked = true")
    List<String> findBookmarkedPosts(@Param("memberUuid") String memberUuid);
}
