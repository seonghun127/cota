package com.cota.domain;

import java.time.LocalDateTime;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
//JPA Entity 클래스들이 아래 클래스 상속할 경우
//필드들(createdDate, modifiedDate)도 컬럼으로 인식
@MappedSuperclass
//import auditing function
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {

	// save that time automatically whenever entity is saved
	@CreatedDate
	private LocalDateTime createdDate;
	
	// save that time automatically whenever entity is updated
	@LastModifiedDate
	private LocalDateTime modifiedDate;
}