package com.qf.controller;

import com.qf.entity.*;
import com.qf.mapper.CarMapper;
import com.qf.mapper.CityMapper;
import com.qf.mapper.OrderMapper;
import com.qf.mapper.UserMapper;
import com.qf.service.BackCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private CarMapper carMapper;

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BackCarService backCarService;

    //跳转到订单提交页面
    @RequestMapping("/tosubmit")
    public String toOrderSubmit(Integer getid,Integer backid,Integer cid){
        return "order/ordersubmit";
    }

    //订单提交页面ajax
    @RequestMapping("/submit")
    public String ordersubmit(Integer getid,Integer backid,Integer cid){
        return "order/ordersubmit";
    }

    //订单提交
    @RequestMapping("/add")
    @ResponseBody//参数cid是car的id、getid和backid是城市的id
    public String addOrder(HttpSession session, Integer cid, Integer getid, Integer backid){
        //System.out.println(getid+"=="+backid+"=="+cid);
        if (session.getAttribute("user") != null){
            //根据cid查询car的price
            Car car = carMapper.findById(cid);
            Double price = car.getPrice()+1035;
            //根据session查询用户的id
            User user = userMapper.findByTelPwd((String) session.getAttribute("user"), "");
            //订单添加
            Order order = new Order();
            order.setUid(user.getId());
            order.setGetid(getid);
            order.setBackid(backid);
            order.setCid(car.getId());
            order.setOprice(price);
            //订单状态
            order.setStatus("已预订");
            orderMapper.addOrder(order);

            //还车
            BackCar backCar = new BackCar();
            backCar.setUid(user.getId());
            backCar.setGetid(getid);
            backCar.setBackid(backid);
            backCar.setCid(car.getId());
            backCar.setOprice(price);
            backCar.setStatus("租车中");
            backCar.setVerify("待审核");
            backCarService.addBackCar(backCar);
            //System.out.println("订单提交id"+backCar.getId());
            return "success";
        }
        return null;
    }

    /**
     * 用户所有订单
     */
    @RequestMapping("/all")
    @ResponseBody
    public List<Object> all(HttpSession session,Integer page){
        //System.out.println(page);
        ArrayList<Object> list = new ArrayList<>();
        if (session.getAttribute("user") != null){
            User user = userMapper.findByTelPwd((String) session.getAttribute("user"), "");
            //System.out.println("=="+user.getId());
            List<Order> orderList = orderMapper.findAllOrders(user.getId(),(page-1)*5);
            Integer count = orderMapper.countOrders(user.getId());
            list.add(orderList);
            list.add(count);
            return list;
        }
        return null;
    }
}
