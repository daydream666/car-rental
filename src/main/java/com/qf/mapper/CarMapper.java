package com.qf.mapper;

import com.qf.entity.Car;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarMapper {

    public List<Car> findAll(Integer getid);

    public List<Car> orderByNum(Integer getid);

    //前台页面订单信息回显
    public Car findById(Integer id);

    //后台汽车查询分页
    public List<Car> selectAll(Integer page);
    public Integer countCar();

    public void deleteCarById(Integer id);

    //后台car修改页面数据回显
    public Car selectById(Integer id);

    //后台car数据修改
    public void updateCar(Car car);

    //后台car添加
    public void addCar(Car car);
}
