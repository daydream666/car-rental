package com.qf.service.impl;

import com.qf.entity.BackCar;
import com.qf.mapper.BackCarMapper;
import com.qf.service.BackCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BackCarServiceImpl implements BackCarService {

    @Autowired
    private BackCarMapper backCarMapper;

    @Override
    public void addBackCar(BackCar backCar) {
        backCarMapper.addBackCar(backCar);
    }

    @Override
    public BackCar getBackCar(BackCar backCar) {
        return backCarMapper.getBackCar(backCar);
    }

    @Override
    public BackCar getBackCarById(Integer id) {
        return backCarMapper.getBackCarById(id);
    }

    @Override
    public BackCar selectBackCar(BackCar backCar) {
        return backCarMapper.selectBackCar(backCar);
    }

    @Override
    public List<BackCar> selectAllBackCar(Integer page) {
        return backCarMapper.selectAllBackCar(page);
    }

    @Override
    public Integer countBackCar() {
        return backCarMapper.countBackCar();
    }

    @Override
    public void updateBackCarStatus(BackCar backCar) {
        backCarMapper.updateBackCarStatus(backCar);
    }


}
