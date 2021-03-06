package com.cota.domain;

import java.time.LocalDateTime;

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
public class Comments extends BaseTimeEntity{
	/*
	 * <COMMENTS>

		create table comments(
			c_no bigint not null auto_increment,
			c_content varchar(255) not null,
			c_pno bigint not null,
			c_uno varchar(255) not null,
			created_date datetime,
			modified_date datetime,
			primary key(c_no),
			foreign key(c_pno)
			references posts(p_no) on delete cascade on update cascade
		);
	*/
	
	@Id	// PK
	// configure auto-increment option to PK in mysql
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "c_no")
	private Long cNo;
	
	@Column(nullable = false)
	private String cContent;
	
	@Column
	private Long cPno;
	
	@Column(nullable = false)
	private Long cUno;
	
	@Builder
	public Comments (Long cNo, String cContent, Long cPno, Long cUno, LocalDateTime createdDate) {
		this.cNo = cNo;
		this.cContent = cContent;
		this.cPno = cPno;
		this.cUno = cUno;
		super.setCreatedDate(createdDate);
	}
	
}
