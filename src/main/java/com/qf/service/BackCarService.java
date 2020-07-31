package com.qf.service;

import com.qf.entity.BackCar;

import java.util.List;

public interface BackCarService {

    public void addBackCar(BackCar backCar);

    public BackCar getBackCar(BackCar backCar);

    public BackCar getBackCarById(Integer id);

    public BackCar selectBackCar(BackCar backCar);

    public List<BackCar> selectAllBackCar(Integer page);
    public Integer countBackCar();

    public void updateBackCarStatus(BackCar backCar);
}
