package com.example.ssodemo.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class UsersVO extends Users{

    private String userUniqueToken;

    public UsersVO(Users user){
    }
}
