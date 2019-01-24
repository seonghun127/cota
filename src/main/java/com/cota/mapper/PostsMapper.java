package com.cota.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cota.dto.PostsListDto;

@Repository
public interface PostsMapper {
	
	// retrieve all or one post(s)
	List<PostsListDto> retrieveAsPostsListDto(Map<String, Object> param);
}
