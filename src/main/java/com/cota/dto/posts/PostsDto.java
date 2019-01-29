package com.cota.dto.posts;

import java.util.HashMap;
import java.util.Map;

import com.cota.domain.Posts;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class PostsDto{

    private Long pNo;

    private  Map<String, Object> param  = new HashMap<String, Object>();

    public abstract Posts toEntity();
}