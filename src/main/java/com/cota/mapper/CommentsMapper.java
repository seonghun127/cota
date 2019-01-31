package com.cota.mapper;

import java.util.List;
import java.util.Map;

import com.cota.dto.comments.*;

import org.springframework.stereotype.Repository;

@Repository
public interface CommentsMapper{
    
    // read data for comments_list
	List<CommentsListDto> retrieveAsCommentsListDto(Map<String, Object> param);
}