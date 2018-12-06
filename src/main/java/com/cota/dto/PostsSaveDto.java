package com.cota.dto;

import com.cota.domain.Posts;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostsSaveDto{
	
	private String title;
	private String content;
	private String author;
	private String hashtag;
	
	public Posts toEntity(){
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .hashtag(hashtag)
                .build();
    }
}
