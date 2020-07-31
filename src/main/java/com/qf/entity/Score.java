package com.qf.entity;

import lombok.Data;

@Data
public class Score {
    private Integer id;
    private Integer uid;
    private Integer reward;
    //用户和积分一对一关系
    private User user;
}
