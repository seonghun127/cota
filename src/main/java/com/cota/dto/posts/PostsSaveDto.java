package com.cota.dto.posts;

import com.cota.domain.Posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostsSaveDto{
	
	private String pTitle;
	private String pContent;
	private Long pUno;
	private String pThumbnail;
	
	// change java entity to data entity for using repository method
	public Posts toEntity(){
		
        return Posts.builder()
                .pTitle(pTitle)
                .pContent(pContent)
                .pUno(pUno)
                .pThumbnail(pThumbnail)
                .build();
    }
	
	@Builder
	public PostsSaveDto(String pTitle, String pContent, Long pUno, String pThumbnail) {
		this.pTitle = pTitle;
		this.pContent = pContent;
		this.pUno = pUno;
		this.pThumbnail = pThumbnail;
	}
}
