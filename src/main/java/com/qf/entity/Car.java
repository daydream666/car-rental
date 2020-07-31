package com.qf.entity;

import lombok.Data;


@Data
public class Car {
    private Integer id;
    private String name;
    private String type;
    private Integer sitnum;
    private Integer cid;
    private Double price;
    private Integer number;
    private String picture;
    private City city;

}
