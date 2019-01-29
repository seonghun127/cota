package com.cota.dto.posts;

import com.cota.domain.Posts;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostsDeleteDto extends PostsDto{
	
	// retrieve post detail using JPA not mybatis,
	// so don't need to use dto and access domain directly.
	// this class is not used.
	
	private Long pNo;

    @Override
    public Posts toEntity() {
        return null;
    }
}
