package com.cota.dto.comments;

import com.cota.domain.Comments;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentsDeleteDto extends CommentsDto{
    
    private Long cNo;

    @Override
    public Comments toEntity() {
        return null;
    }
    
}