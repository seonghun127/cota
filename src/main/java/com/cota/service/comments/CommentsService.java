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
public class CommentsService{

    // jpa
    CommentsRepository commentsRepository;

    // mybatis
    CommentsMapper commentsMapper;

    // ------------------------------------JPA-------------------------------------- //

    @Transactional
    public Comments findByCNo(Long cNo){
        return commentsRepository.findByCNo(cNo);
    }
    
    @Transactional
    public void deleteCommentsById(Long cNo){
        commentsRepository.deleteById(cNo);
    }

    @Transactional
    public Long  saveComments(CommentsSaveDto dto){
        return commentsRepository.save(dto.toEntity()).getCNo();
    }

    @Transactional
    public void updateComments(CommentsUpdateDto dto){
        commentsRepository.save(dto.toEntity());
    }

    // ----------------------------------Mybatis----------------------------------- //

    @Transactional
    public List<CommentsListDto> findComment(Map<String, Object> param) {
    	return commentsMapper.retrieveAsCommentsListDto(param);
    }

}