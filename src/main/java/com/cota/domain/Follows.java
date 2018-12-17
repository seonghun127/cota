package com.cota.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
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
			f_follower_no bigint not null,
			f_followed_no bigint not null,
			primary key(f_follower_no, f_followed_no),
			foreign key(f_followed_no)
			references posts(p_no) on delete no action on update no action
		);
	*/
	
	@Id
	@Column(name = "f_follower_no")
	private Long fFollwerNo;
	
	@Id
	@Column(name = "f_followed_no")
	private Long fFollowedNo;
}
