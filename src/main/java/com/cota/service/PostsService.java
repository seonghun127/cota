package com.cota.service;

import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import com.cota.dto.posts.*;
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
    public Long updatePost(PostsUpdateDto dto) {
        return postsRepository.save(dto.toEntity()).getPNo();
    }
    
    @Transactional
    public void deletePostsById(Long pNo) {
    	postsRepository.deleteById(pNo);
    }
    
    // ----------------------------------Mybatis----------------------------------- //
    
    // retrieve all or one post(s)
    @Transactional
    public List<PostsListDto> findPost(Map<String, Object> param) {
    	return postsMapper.retrieveAsPostsListDto(param);
    }
}
