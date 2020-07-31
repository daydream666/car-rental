package com.qf.mapper;

import com.qf.entity.BackCar;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BackCarMapper {

    public void addBackCar(BackCar backCar);

    public BackCar getBackCar(BackCar backCar);

    public BackCar getBackCarById(Integer id);

    public BackCar selectBackCar(BackCar backCar);

    public List<BackCar> selectAllBackCar(Integer page);
    public Integer countBackCar();

    public void updateBackCarStatus(BackCar backCar);
}
