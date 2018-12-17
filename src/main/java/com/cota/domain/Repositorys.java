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
public class Repositorys implements Serializable{
	
	/*
	 * <REPOSITORY>

		create table repositorys(
			r_uno bigint not null,
			r_pno bigint not null,
			primary key(r_uno, r_pno),
			foreign key(r_pno)
			references posts(p_no) on delete cascade on update cascade
		);
	*/
	
	@Id
	@Column(name="r_uno")
	private Long rUno;
	
	@Id
	@Column(name="r_pno")
	private Long rPno;
}
