package com.cota.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cota.domain.Posts;

public interface PostsRepository extends JpaRepository<Posts, Long> {
	
	@Modifying
	@Query("UPDATE Posts p SET p.pViewCnt = p.pViewCnt + 1 WHERE p.pNo = :pNo")
	void updateViewCount(@Param("pNo") Long pNo);
	
	@Modifying
	@Query("UPDATE Posts p SET p.pTitle = :pTitle,"
			+ "p.pContent = :pContent,"
			+ "p.pHashtag = :pHashtag,"
			+ "p.pThumbnail = :pThumbnail WHERE p.pNo = :pNo")
	void updatePosts(@Param("pNo") Long pNo, @Param("pTitle") String pTitle,
			@Param("pContent") String pContent, @Param("pHashtag") String pHashtag,
			@Param("pThumbnail") String pThumbnail);

}
