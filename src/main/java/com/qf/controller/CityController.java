package com.qf.controller;

import com.qf.entity.City;
import com.qf.mapper.CityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/city")
public class CityController {

    @Autowired
    private CityMapper cityMapper;
    @Autowired
    private JedisPool pool;

    @RequestMapping("/select")
    @ResponseBody
    public List<City> selectCity(Integer pid){
        //System.out.println("pid="+pid);
        if (pid!=null){
            List<City> cityList = cityMapper.findAll(pid);
            return cityList;
        }
        return null;
    }

    @RequestMapping("/citys")
    @ResponseBody
    public ArrayList<City> citys(Integer getid,Integer backid){
        ArrayList<City> list = new ArrayList<>();
        //System.out.println(getid);
        if (getid!=null && backid != null){
            City city1 = cityMapper.findById(getid);
            City city2 = cityMapper.findById(backid);
            list.add(city1);
            list.add(city2);
        }
        return list;
    }

    //跳转后台汽车管理
    @RequestMapping("/toCity")
    public String toCar(){
        return "after/city";
    }

    /**
     * 城市后台分页
     */
    @RequestMapping("/allCity")
    @ResponseBody
    public List<Object> selectAllCity(Integer page){
        //System.out.println(page);
        ArrayList<Object> object = new ArrayList<>();
        List<City> list = cityMapper.selectAllCity((page-1)*5);
        Integer dataCount = cityMapper.countCity();
        object.add(list);
        object.add(dataCount);
        return object;
    }

    /**
     * 后台删除城市
     */
    @RequestMapping("/delCity")
    public String delCity(String name){
        //System.out.println(name);
        if (name != null){
            cityMapper.delCityByName(name);
            return "redirect:/city/toCity";
        }
        return "";
    }

    /**
     * 跳转到后台城市修改页面
     */
    @RequestMapping("/toCityUpdate")
    public String toCityUpdate(String name, Model model){
        //name不为null是修改city，为null是添加city
        City city = null;
        if (name != null){
            city = cityMapper.findByName(name);
        }
        model.addAttribute("city",city);
        return "after/cityupdate";
    }

    /**
     * 后台添加修改城市
     */
    @RequestMapping("/updateCity")
    public String updateCity(City city){
        //System.out.println("修改city:"+city);
        if (city.getId() != null){
            //修改city
            //转换和数据库字段一致的字段值
            City city1 = new City();
            city1.setId(city.getId());
            city1.setName(city.getName1());
            cityMapper.updateCityById(city1);
        }else {
            //添加city
            City city1 = new City();
            city1.setName(city.getName1());
            //city的name已存在,直接添加门店name1
            City c = cityMapper.selectByName(city.getName());
            if (c != null){
                city1.setPid(c.getId());
                cityMapper.addCityName1(city1);
            }else {
                //city的name不存在就添加city的name，name1
                int id = cityMapper.addCityName(city);
                city1.setPid(city.getId());
                cityMapper.addCityName1(city1);
            }

        }
        return "redirect:/city/toCity";
    }

    /**
     * 校验添加city
     */
    @RequestMapping("/verifyCityName")
    @ResponseBody
    public String verifyCityName(String name1,String name){
        //System.out.println("name="+name+"  name1="+name1);
        //判断页面表单门店输入框是否为空
        if ( (name == "" || name == null) && (name1 == "" || name1 == null)){
            return "empty";
        }else {
            //判断city的name1门店是否存在
            City city = cityMapper.selectByName(name1);
            if (city != null){
                return "error";
            }else {
                return "success";
            }
        }
    }
}
