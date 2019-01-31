package com.cota.dto.comments;

import java.util.Date;

import com.cota.domain.Comments;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentsListDto extends CommentsDto{
    
    /*
        c.c_no,
        c.c_content,
        c.c_uno,
        modified_date,
        u.u_no,
        u.u_name,
        u.u_email
    */
    private Long cNo;
    private String cContent;
    private Long cUno;
    private Date modifiedDate;
    private Long uNo;
    private String uName;
    private String uEmail;

    @Override
    public Comments toEntity() {
        return null;
    }
}