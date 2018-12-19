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
public class Repositorys implements Serializable{
	
	/*
	 * <REPOSITORY>

		create table repositorys(
		r_no bigint not null auto_increment,
		r_uno bigint not null,
		r_pno bigint not null,
		primary key(r_no),
		foreign key(r_pno)
		references posts(p_no) on delete cascade on update cascade,
		foreign key(r_uno)
		references users(u_no) on delete cascade on update cascade

);
	*/
	
	@Id	// PK
	// configure auto-increment option to PK in mysql
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long rNo;
	
	@Column(nullable = false)
	private Long rUno;
	
	@Column(nullable = false)
	private Long rPno;
	
	@Column(nullable = false)
	private boolean rCheck;
}
