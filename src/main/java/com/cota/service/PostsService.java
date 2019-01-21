package com.cota.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import com.cota.domain.Posts;
import com.cota.dto.PostsDetailDto;
import com.cota.dto.PostsListDto;
import com.cota.dto.PostsSaveDto;
import com.cota.mapper.PostsMapper;
import com.cota.repository.PostsRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PostsService {
	
	// jpa
    private PostsRepository postsRepository;
    
    // mybatis
    private PostsMapper postsMapper;

    // ------------------------------------JPA-------------------------------------- //

    @Transactional
    public Long save(PostsSaveDto dto){
        return postsRepository.save(dto.toEntity()).getPNo();
    }
    
    @Transactional
    public Optional<Posts> findById(Long pNo) {
    	return postsRepository.findById(pNo);
    }
    
    @Transactional
    public void updatePosts(Long pNo, String pTitle, String pContent,
    		String pThumbnail) {
    	postsRepository.updatePosts(pNo, pTitle, pContent, pThumbnail);
    }
    
    @Transactional
    public void deletePostsById(Long pNo) {
    	postsRepository.deleteById(pNo);
    }
    
    // ----------------------------------Mybatis----------------------------------- //
    
    @Transactional
    public List<PostsListDto> findAll(Map<String, Object> param) {
    	return postsMapper.retrieveAllAsPostsListDto(param);
    }

}
