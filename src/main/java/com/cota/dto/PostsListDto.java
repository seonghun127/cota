package com.cota.dto;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostsListDto{
	
	// unlike PostsDetailDto class, this class is used with mybatis.
	// to retrieve optional column data, 'post list' need the return type of this class.
	
	private Long pNo;
	private String pTitle;
	private String pContent;
	private String uName;
	private String uEmail;
	private Date modifiedDate;
	private String pThumbnail;
	
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
