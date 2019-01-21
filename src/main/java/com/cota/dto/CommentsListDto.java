package com.cota.dto;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentsListDto{
    
    /*
        c.c_no,
        c.c_content,
        c.c_uno,
        modified_date,
        u.u_no,
        u.u_name,
        u.u_email
    */
    Long cNo;
    String cContent;
    Long cUno;
    Date ModifiedDate;
    Long uNo;
    String uName;
    String uEmail;
}