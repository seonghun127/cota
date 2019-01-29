package com.cota.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cota.domain.Posts;
import com.cota.dto.posts.PostsUpdateDto;

public interface PostsRepository extends JpaRepository<Posts, Long> {

	@Query("SELECT p.pTitle, p.pContent, p.pUno, p.pThumbnail FROM Posts p " 
		+ "WHERE  p.pNo = :pNo")
	PostsUpdateDto findByPno(Long pNo);
}
