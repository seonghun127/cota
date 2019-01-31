package com.cota.service.posts;

import java.util.List;

import com.cota.domain.Posts;
import com.cota.dto.posts.PostsDto;
import com.cota.dto.posts.PostsListDto;

public interface PostsService{

    public Posts findByPNo(PostsDto dto);
    
    public Long savePost(PostsDto dto);

    public Long updatePost(PostsDto dto);

    public void deletePostsById(PostsDto dto);

    public List<PostsListDto> findPost(PostsDto dto);

}