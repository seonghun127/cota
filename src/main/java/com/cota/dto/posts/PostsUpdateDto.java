package com.cota.dto.posts;


import java.time.LocalDateTime;

import com.cota.domain.Posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostsUpdateDto extends PostsDto{

	private Long pNo;
	private String pTitle;
	private String pContent;
	private Long pUno;
	private String pThumbnail;
	private LocalDateTime createdDate;
	
	// change java entity to data entity for using repository method
	public Posts toEntity(){

		return Posts.builder()
				.pNo(pNo)
                .pTitle(pTitle)
                .pContent(pContent)
                .pUno(pUno)
				.pThumbnail(pThumbnail)
				.createdDate(createdDate)
                .build();
	}
	
	@Builder
	public PostsUpdateDto (Long pNo, String pTitle, String pContent, Long pUno, String pThumbnail, LocalDateTime createdDate){
		this.pNo = pNo;
		this.pTitle = pTitle;
		this.pContent = pContent;
		this.pUno = pUno;
		this.pThumbnail = pThumbnail;
		this.createdDate = createdDate;
	}
}
