package com.cota.dto.comments;

import java.util.HashMap;
import java.util.Map;

import com.cota.domain.Comments;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class CommentsDto {
    private Long cNo;

    private  Map<String, Object> param  = new HashMap<String, Object>();

    public abstract Comments toEntity();
}