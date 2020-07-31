package com.qf.entity;

import lombok.Data;

@Data
public class Order {
    private Integer id;
    private Integer uid;
    private Integer cid;
    private Integer getid;
    private Integer backid;
    private Double oprice;
    private String status;
    private User user;
    private Car car;
    private City city1;
    private City city2;
}
