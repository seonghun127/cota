package com.cota.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostsUpdateDto {
	String pTitle;
	String pContent;
	String pHashtag;
	String pThumbnail;
}
