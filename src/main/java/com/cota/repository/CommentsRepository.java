package com.cota.repository;

import com.cota.domain.Comments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentsRepository extends JpaRepository<Comments, Long>{
 
    @Modifying
	@Query("UPDATE Comments c SET c.cContent = :cContent"
			+ " WHERE c.cNo = :cNo")
	void updateComments(@Param("cNo") Long cNo, @Param("cContent") String cContent);
}