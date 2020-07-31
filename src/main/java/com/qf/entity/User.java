package com.qf.entity;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String tel;
    private String password;
    private String email;
    private String invitation;
    private String image;
    private String nickname;
    private String birthday;
}
