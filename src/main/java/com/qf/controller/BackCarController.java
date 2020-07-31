package com.qf.controller;

import com.qf.entity.BackCar;
import com.qf.service.BackCarService;
import com.qf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/backcar")
public class BackCarController {

    @Autowired
    private BackCarService backCarService;
    @Autowired
    private UserService userService;

    @RequestMapping("/get")
    @ResponseBody
    public String all(Integer id){//id 是还车id
        //System.out.println("id="+id);
        if (id != 0 && id != 1){
            BackCar backCar = backCarService.getBackCarById(id);
            if (backCar.getStatus().equals("租车中")){
                return "reviewed";
            }else if (backCar.getStatus().equals("已归还")){
                return "backed";
            }
        }else if (id == 0){
            //订单管理(mymain)页面id=0说明用户没有租车/租车完成
            return "backed";
        }else if (id == 1){
            //还车管理(backcar)页面
            return "complete";
        }
        return null;
    }

    //跳转后台车辆审核页面
    @RequestMapping("/toBackCar")
    public String toCar(){
        return "after/carcheck";
    }

    //后台所有车辆审核数据回显和分页
    @RequestMapping("/allBackCar")
    @ResponseBody
    public List<Object> selectAll(Integer page){
        //System.out.println(page);
        ArrayList<Object> object = new ArrayList<>();
        List<BackCar> list = backCarService.selectAllBackCar((page - 1) * 5);
        Integer dataCount = backCarService.countBackCar();
        object.add(list);
        object.add(dataCount);
        return object;
    }
    //后台汽车审核
    @RequestMapping("/check")
    @ResponseBody
    public String check(Integer id){
        //System.out.println(id);
        if (id != null){
            BackCar backCar = new BackCar();
            backCar.setStatus("已归还");
            backCar.setVerify("已审核");
            backCar.setId(id);
            backCarService.updateBackCarStatus(backCar);
            return "success";
        }
        return "error";
    }
}
