package com.qf.mapper;

import com.qf.entity.City;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityMapper {

    public List<City> findAll(Integer pid);

    public City findById(Integer getid);

    //后台城市分页
    public List<City> selectAllCity(Integer page);

    public Integer countCity();

    //删除城市
    public void delCityByName(String name);

    //后台查询一条城市信息(修改city数据回显)
    public City findByName(String name);

    //后台修改一条city信息
    public void updateCityById(City city);

    public int addCityName(City city);
    public void addCityName1(City city);

    //查询city门店是否存在
    public City selectByName(String name);

    //查询所有门店
    public List<City> findAllName1();
}
