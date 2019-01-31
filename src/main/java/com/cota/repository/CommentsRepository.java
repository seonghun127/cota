package com.cota.repository;

import com.cota.domain.Comments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentsRepository extends JpaRepository<Comments, Long>{
 
	@Query("SELECT c FROM Comments c WHERE c.cNo = :cNo")
    Comments findByCNo(@Param("cNo") Long cNo);
}