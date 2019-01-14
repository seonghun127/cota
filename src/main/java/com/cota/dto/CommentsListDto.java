package com.cota.dto;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentsListDto{
    
    Long cNo;
    String cContent;
    Long cPno;
    String cUno;
    Date ModifiedDate;
}