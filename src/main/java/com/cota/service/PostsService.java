package com.cota.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.cota.dto.PostsSaveDto;
import com.cota.repository.PostsRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PostsService {
	
    private PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveDto dto){
        return postsRepository.save(dto.toEntity()).getId();
    }
}
