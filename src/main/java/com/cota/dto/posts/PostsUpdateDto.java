package com.cota.dto.posts;


import com.cota.domain.Posts;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;

@Getter
@Setter
@NoArgsConstructor
public class PostsUpdateDto {

	private Long pNo;
	private String pTitle;
	private String pContent;
	private Long pUno;
	private String pThumbnail;
	
	// change java entity to data entity for using repository method
	public Posts toEntity(){
		return Posts.builder()
				.pNo(pNo)
                .pTitle(pTitle)
                .pContent(pContent)
                .pUno(pUno)
                .pThumbnail(pThumbnail)
                .build();
    }
}
