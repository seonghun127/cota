package com.cota.service.posts;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.cota.domain.Posts;
import com.cota.dto.posts.*;
import com.cota.mapper.PostsMapper;
import com.cota.repository.PostsRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PostsServiceImpl implements PostsService{
	
	// jpa
    private PostsRepository postsRepository;
    
    // mybatis
    private PostsMapper postsMapper;

    // ------------------------------------JPA-------------------------------------- //

    @Transactional
    public Posts findByPNo(PostsDto dto){
        return postsRepository.findByPNo(dto.getPNo());
    }

    @Transactional
    public Long savePost(PostsDto dto){
        return postsRepository.save(dto.toEntity()).getPNo();

    }
    
    @Transactional
    public Long updatePost(PostsDto dto){
        return postsRepository.save(dto.toEntity()).getPNo();
    }
    
    @Transactional
    public void deletePostsById(PostsDto dto) {
    	postsRepository.deleteById(dto.getPNo());
    }
    
    // ----------------------------------Mybatis----------------------------------- //
    
    // retrieve all or one post(s)
    @Transactional
    public List<PostsListDto> findPost(PostsDto dto) {
    	return postsMapper.retrieveAsPostsListDto(dto.getParam());
    }
}
