package com.cota.service;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import com.cota.dto.CommentsListDto;
import com.cota.dto.CommentsSaveDto;
import com.cota.mapper.CommentsMapper;
import com.cota.repository.CommentsRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CommentsService{

    // jpa
    CommentsRepository commentsRepository;

    // mybatis
    CommentsMapper commentsMapper;

    // ------------------------------------JPA-------------------------------------- //

    @Transactional
    public void deleteCommentsById(Long cNo){
        commentsRepository.deleteById(cNo);
    }

    @Transactional
    public void saveComments(CommentsSaveDto dto){
        commentsRepository.save(dto.toEntity());
    }

    @Transactional
    public void updateComments(Long cNo, String cContent){
        commentsRepository.updateComments(cNo, cContent);
    }

    // ----------------------------------Mybatis----------------------------------- //

    @Transactional
    public List<CommentsListDto> findAll(Map<String, Object> param) {
    	return commentsMapper.retrieveAllAsCommentsListDto(param);
    }

}