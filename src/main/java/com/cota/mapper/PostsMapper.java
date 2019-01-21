package com.cota.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cota.dto.PostsListDto;

@Repository
public interface PostsMapper {
	
	// read data for card_list
	List<PostsListDto> retrieveAllAsPostsListDto(Map<String, Object> param);

}
