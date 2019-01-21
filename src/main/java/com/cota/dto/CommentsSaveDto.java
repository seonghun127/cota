package com.cota.dto;

import com.cota.domain.Comments;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentsSaveDto{

    private String cContent;
    private Long cPno;
    private Long cUno;

    // change java entity to data entity for using repository method
	public Comments toEntity(){
        return Comments.builder()
                .cContent(cContent)
                .cPno(cPno)
                .cUno(cUno)
                .build();
    }
}