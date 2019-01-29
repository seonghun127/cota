package com.cota.dto.users;

import com.cota.domain.Users;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UsersSaveDto {

    private String uName;
    private String uEmail;	
    
    public Users toEntity(){
        return Users.builder()
                    .uName(uName)
                    .uEmail(uEmail)
                    .build();
    }

    @Builder
    public UsersSaveDto (String uName, String uEmail){
        this.uName = uName;
        this.uEmail = uEmail;
    }
}