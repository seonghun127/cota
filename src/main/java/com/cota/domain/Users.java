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
public class Users{

	/*
	 * <USERS>

		create table users(
			u_no bigint not null auto_increment,
			u_name varchar(255) not null,
			u_email varchar(255) not null
			primary key(u_no)
		);
	 * */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long uNo;
	
	@Column(nullable = false)
	private String uName;
	
	@Column(nullable = false)
	private String uEmail;	
	
	@Builder
	public Users(String uName, String uEmail) {
		this.uName = uName;
		this.uEmail = uEmail;
	}
	
	
}
