package com.cota.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import com.cota.domain.Posts;
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

    @Transactional
    public Long save(PostsSaveDto dto){
        return postsRepository.save(dto.toEntity()).getPNo();
    }
    
    @Transactional
    public Optional<Posts> findById(Long pNo) {
    	return postsRepository.findById(pNo);
    }
    
    @Transactional
    public List<PostsListDto> findAll(Map<String, Object> param) {
    	return postsMapper.retrieveAllAsPostsListDto(param);
    }
    
    @Transactional
    public boolean getLikeCheck(Map<String, String> param) {
    	return postsMapper.getLikeCheck(param);
    }
    
    @Transactional
    public boolean getFollowCheck(Map<String, String> param) {
    	return postsMapper.getFollowCheck(param);
    }
    
    @Transactional
    public boolean getRepositoryCheck(Map<String, String> param) {
    	return postsMapper.getRepositoryCheck(param);
    }
    
    @Transactional
    public int getCount() {
    	return postsMapper.postsCount();
    }
}
