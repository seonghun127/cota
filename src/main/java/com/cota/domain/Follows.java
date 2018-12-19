package com.cota.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

//create basic constructor whose type is 'PROTECTED'
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter // create getter method automatically
@Entity
public class Follows implements Serializable{

	/*
	 * <FOLLOWS>

		create table follows(
		f_no bigint not null auto_increment,
		f_follower_no bigint not null,
		f_followed_no bigint not null,
		f_check tinyint(1) not null,
		primary key(f_no),
		foreign key(f_followed_no)
		references posts(p_uno) on delete no action on update no action
);
	*/
	@Id	// PK
	// configure auto-increment option to PK in mysql
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long fNo;
	
	@Column(nullable = false)
	private Long fFollowerNo;
	
	@Column(nullable = false)
	private Long fFollowedNo;
	
	@Column(nullable = false)
	private boolean fCheck;
}
