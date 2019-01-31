package com.cota.service.comments;

import java.util.List;

import com.cota.domain.Comments;
import com.cota.dto.comments.CommentsDto;
import com.cota.dto.comments.CommentsListDto;

public interface CommentsService{

    public Comments findByCNo(CommentsDto dto);
    
    public void deleteCommentsById(CommentsDto dto);

    public Long  saveComments(CommentsDto dto);

    public void updateComments(CommentsDto dto);

    public List<CommentsListDto> findComment(CommentsDto dto);
}