package com.cota.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

//create basic constructor whose type is 'PROTECTED'
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter // create getter method automatically
@Entity	// create Posts Table
public class Posts extends BaseTimeEntity{
	
	/*
	 * p_no bigint not null auto_increment,
	p_title varchar(255) not null,
	p_content text not null,
	p_uno bigint not null,
	p_thumbnail varchar(255) not null,
	p_hashtag varchar(255) not null,
	p_like_cnt int default 0,
	p_view_cnt int default 0,
	p_comment_cnt int default 0,
	p_created_date datetime,
	p_modified_date datetime,
	primary key(p_no)

	 * */
	
	@Id	// PK
	// configure auto-increment option to PK in mysql
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long pNo;
	
	// re-configure column to use usefully in JAVA
	//@Column(name = "p_title")
	private String pTitle;
	
	private Long pUno;
	
	// re-configure column to  use more useful in JAVA (example)
	//@Column(columnDefinition="TEXT", nullable = false)
	private String pContent;
	
	private String pHashtag;
	
	private String pThumbnail;
	
	private int pLikeCnt;
	
	private int pCommentCnt;
	
	private int pViewCnt;
	
	// 생성자 상단에 선언시 생성자에 포함된 필드만 빌더에 포함
	@Builder
	public Posts(String pTitle, String pContent, Long pUno, String pHashtag, String pThumbnail) {
		this.pTitle = pTitle;
		this.pContent = pContent;
		this.pUno = pUno;
		this.pHashtag = pHashtag;
		this.pThumbnail = pThumbnail;
	}
	
}