package com.qf.entity;

import lombok.Data;

@Data
public class City {
    private Integer id;
    private String name;
    private Integer pid;

    //城市门店地址
    private String name1;
    //城市门店地址id
    private Integer id1;
}
