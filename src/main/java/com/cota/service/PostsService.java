package com.cota.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cota.controller.PostsController;
import com.cota.domain.Posts;
import com.cota.dto.PostsSaveDto;
import com.cota.repository.PostsRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PostsService {
	
	public static final Logger logger = LoggerFactory.getLogger(PostsService.class);
	
    private PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveDto dto){
    	
    	logger.info("pContent is " + dto.toEntity().getPContent());
    	
        return postsRepository.save(dto.toEntity()).getPNo();
    }
    
    @Transactional
    public Optional<Posts> findById(Long pNo) {
    	return postsRepository.findById(pNo);
    }
}
