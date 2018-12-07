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
	
	@Id	// PK
	// configure auto-increment option to PK in mysql
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	// re-configure column to use usefully in JAVA
	@Column(length=500, nullable = false)
	private String title;
	
	// re-configure column to  use more useful in JAVA
	@Column(columnDefinition="TEXT", nullable = false)
	private String content;
	
	// auto matching column
	private String author;
	
	private String hashtag;
	
	//private String thumbnail;
	//private int likeCnt;
	//private int commentCnt;
	//private int viewCnt;
	
	// 생성자 상단에 선언시 생성자에 포함된 필드만 빌더에 포함
	@Builder
	public Posts(String title, String content, String author, String hashtag) {
		this.title = title;
		this.content = content;
		this.author = author;
		this.hashtag = hashtag;
	}
	
}