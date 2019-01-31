package com.cota.dto.comments;

import java.time.LocalDateTime;

import com.cota.domain.Comments;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentsUpdateDto extends CommentsDto{

    private Long cNo;
    private String cContent;
    private Long cPno;
    private Long cUno;
    private LocalDateTime createdDate;

    // change java entity to data entity for using repository method
	public Comments toEntity(){
        return Comments.builder()
                .cNo(cNo)
                .cContent(cContent)
                .cPno(cPno)
                .cUno(cUno)
                .createdDate(createdDate)
                .build();
    }
}