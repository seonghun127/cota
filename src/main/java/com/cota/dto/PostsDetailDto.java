package com.cota.dto;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostsDetailDto {
	
	// retrieve post detail using JPA not mybatis,
	// so don't need to use dto and access domain directly.
	// this class is not used.
	
	private Long pNo;

	private String pTitle;
	
	private Long pUno;
	
	private String pContent;
	
	private String pHashtag;
	
	private Date modifiedDate;
	
	private String pThumbnail;
	
	private int pLikeCnt;
	
	private int pCommentCnt;
	
	private int pViewCnt;
}
