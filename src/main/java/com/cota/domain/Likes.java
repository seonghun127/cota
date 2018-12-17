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
@Entity	
public class Likes{
	
	/*
	 * <LIKES>

		create table likes(
			l_no bigint not null auto_increment,
			l_pno bigint not null,
			l_uno bigint not null,
			l_check tinyint(1) not null,
			primary key(l_no),
			foreign key(l_pno)
			references posts(p_no) on delete cascade on update cascade
		);
	*/
	
	@Id	// PK
	// configure auto-increment option to PK in mysql
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long lNo;
	
	@Column(nullable = false)
	private Long lPno;
	
	@Column(nullable = false)
	private Long lUno;
	
	@Column(nullable = false)
	private boolean lCheck;
	
	@Builder
	public Likes (Long lPno, Long lUno, boolean lCheck) {
		this.lPno = lPno;
		this.lUno = lUno;
		this.lCheck = lCheck;
	}
}
