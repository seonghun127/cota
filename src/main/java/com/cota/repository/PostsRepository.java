package com.cota.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cota.domain.Posts;

public interface PostsRepository extends JpaRepository<Posts, Long> {

}
