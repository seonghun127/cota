package com.cota.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

import com.cota.domain.Posts;

public interface PostsRepository extends JpaRepository<Posts, Long> {
	
	// update post contents
	@Modifying
	@Query("UPDATE Posts p SET p.pTitle = :pTitle,"
			+ "p.pContent = :pContent,"
			+ "p.pThumbnail = :pThumbnail WHERE p.pNo = :pNo")
	void updatePosts(@Param("pNo") Long pNo, @Param("pTitle") String pTitle,
			@Param("pContent") String pContent, @Param("pThumbnail") String pThumbnail);

	@Modifying
	@Query("UPDATE Posts p SET p.pTitle = :pTitle,"
			+ "p.pContent = :pContent,"
			+ "p.pThumbnail = :pThumbnail WHERE p.pNo = :pNo")
	void updatePost(Long pNo, Posts posts);
	
}
