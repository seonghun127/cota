package com.cota.service.comments;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import com.cota.domain.Comments;
import com.cota.dto.comments.*;
import com.cota.mapper.CommentsMapper;
import com.cota.repository.CommentsRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CommentsServiceImpl implements CommentsService{

    // jpa
    CommentsRepository commentsRepository;

    // mybatis
    CommentsMapper commentsMapper;

    // ------------------------------------JPA-------------------------------------- //

    @Transactional
    public Comments findByCNo(CommentsDto dto){
        return commentsRepository.findByCNo(dto.getCNo());
    }
    
    @Transactional
    public void deleteCommentsById(CommentsDto dto){
        commentsRepository.deleteById(dto.getCNo());
    }

    @Transactional
    public Long  saveComments(CommentsDto dto){
        return commentsRepository.save(dto.toEntity()).getCNo();
    }

    @Transactional
    public void updateComments(CommentsDto dto){
        commentsRepository.save(dto.toEntity());
    }

    // ----------------------------------Mybatis----------------------------------- //

    @Transactional
    public List<CommentsListDto> findComment(CommentsDto dto) {
    	return commentsMapper.retrieveAsCommentsListDto(dto.getParam());
    }
}