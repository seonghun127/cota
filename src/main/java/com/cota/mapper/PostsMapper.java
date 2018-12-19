package com.cota.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cota.dto.PostsListDto;

@Repository
public interface PostsMapper {
	
	// test method for mybatis connection
	int postsCount();
	
	// read data for card_list
	List<PostsListDto> retrieveAllAsPostsListDto(Map<String, Object> param);
	
	// check like status
	boolean getLikeCheck(Map<String, String> param); 
	
	// check follow status
	boolean getFollowCheck(Map<String, String> param);
	
	// check repository status
	boolean getRepositoryCheck(Map<String, String> param);

}
