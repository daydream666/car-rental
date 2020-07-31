package com.qf.controller;

import com.qf.entity.Car;
import com.qf.entity.City;
import com.qf.mapper.CarMapper;
import com.qf.mapper.CityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarMapper carMapper;

    @Autowired
    private CityMapper cityMapper;

    //首页
    @RequestMapping("/index")
    public String index(){
        return "/pagehome/index";
    }

    @RequestMapping("/toShortRent")
    public String toShortRent(){
        return "shortRent/shortrent";
    }

    @RequestMapping("/toshortsort")
    public String toshortsort(){
        return "shortRent/shortsort";
    }

    //选车页面 按价格排序
    @RequestMapping("/price")
    @ResponseBody
    public List<Car> orderByPrice(Integer getid,Integer backid){
        //System.out.println(getid+"==="+backid);
        if (getid!=null){
            List<Car> carList = carMapper.findAll(getid);
            return carList;
        }
        return null;
    }
    //选车页面 按热度排序
    @RequestMapping("/num")
    @ResponseBody
    public List<Car> orderByNum(Integer getid,Integer backid){
        //System.out.println(getid+"==="+backid);
        if (getid!=null){
            List<Car> carList = carMapper.orderByNum(getid);
            return carList;
        }
        return null;
    }

    @RequestMapping("/findcar")
    @ResponseBody
    public Car findcar(Integer getid,Integer backid,Integer cid){
        //System.out.println(getid+"=="+backid+"=="+cid);
        if (cid!=null){
            Car car = carMapper.findById(cid);
            return car;
        }
        return null;
    }

    //跳转后台汽车管理
    @RequestMapping("/toCar")
    public String toCar(){
        return "after/car";
    }

    //后台汽车管理分页
    @RequestMapping("/all")
    @ResponseBody
    public List<Object> selectAll(Integer page){
        //System.out.println(page);
        ArrayList<Object> object = new ArrayList<>();
        List<Car> list = carMapper.selectAll((page-1)*5);
        Integer dataCount = carMapper.countCar();
        object.add(list);
        object.add(dataCount);
        System.out.println("carList="+list);
        return object;
    }
    /**
     * 后台汽车删除
     */
    @RequestMapping("/del")
    public String delete(Integer id){
        //System.out.println(id);
        carMapper.deleteCarById(id);
        return "redirect:/car/toCar";
    }

    /**
     * 跳转到修改页面
     */
    @RequestMapping("/toCarUpdate")
    public String toUpdateCar(Integer id, Model model){
        System.out.println(id);
        Car car = null;
        if (id != null){
            car = carMapper.selectById(id);
        }
        List<City> name1 = cityMapper.findAllName1();
        model.addAttribute("cityList",name1);
        model.addAttribute("car",car);
        return "after/carupdate";
    }

    /**
     * 添加修改car数据
     */
    @RequestMapping("/updateCar")
    public String updateCar(Car car,MultipartFile carPicture){
        //图片上传的位置
        String path = "D:\\apache-tomcat-7.0.56-windows-x86\\apache-tomcat-7.0.56\\webapps\\car-images";
        File file = new File(path);
        // 判断，该路径是否存在
        if (!file.exists()) {
            // 创建该文件夹
            file.mkdirs();
        }
        String filename = carPicture.getOriginalFilename();
        //截取文件点之后的包括点
        filename = filename.substring(filename.lastIndexOf("."));
        String uuid = UUID.randomUUID().toString().replace("-", "");
        filename = uuid + filename;
        try {
            carPicture.transferTo(new File(path,filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        car.setPicture("http://localhost:8899/car-images/"+filename);
        if (car.getId()!= null){
            //修改car
            //System.out.println("修改car："+car);
            carMapper.updateCar(car);
        }else{
            //添加car
            //System.out.println("添加car："+car);
            carMapper.addCar(car);
        }
        return "redirect:/car/toCar";
    }
}
