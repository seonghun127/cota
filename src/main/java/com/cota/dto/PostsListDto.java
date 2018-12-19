package com.cota.dto;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostsListDto{
	
	private String pTitle;
	private Long pUno;
	private Date modifiedDate;
	private String pThumbnail;
	private int pLikeCnt;
	private int pCommentCnt;
	private int pViewCnt;
	
	/*public PostsListDto (String pTitle, Long pUno, Date modifiedDate, String pThumbnail, int pLikeCnt, int pCommentCnt, int pViewCnt) {
		this.pTitle = pTitle;
		this.pUno = pUno;
		this.modifiedDate = modifiedDate;
		this.pThumbnail = pThumbnail;
		this.pLikeCnt = pLikeCnt;
		this.pCommentCnt = pCommentCnt;
		this.pViewCnt = pViewCnt;
	}*/
}