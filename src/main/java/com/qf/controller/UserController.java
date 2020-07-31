package com.qf.controller;

import com.qf.entity.*;
import com.qf.mapper.UserMapper;
import com.qf.mapper.UserPermissionMapper;
import com.qf.service.BackCarService;
import com.qf.service.ScoreService;
import com.qf.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private BackCarService backCarService;

    @Autowired
    private UserPermissionMapper userPermissionMapper;

    @RequestMapping("/login")
    public String login(){
        return "pagehome/login";
    }

    @RequestMapping(value = "/ajaxLogin")
    @ResponseBody
    public String ajaxLogin(User user, HttpSession session){
        if ((user.getTel() != "" && user.getTel()!= null)
                && (user.getPassword() != "" && user.getPassword() != null)){
            User user1 = userService.findByTelPwd(user.getTel(), user.getPassword());
            //获取用户积分
            Score score = scoreService.getScoreById(user1.getId());
            System.out.println("score="+score);
            if (user1 != null){
                session.setAttribute("user",user.getTel());
                session.setAttribute("score",score.getReward());
                return "1";
            }
        }
        return "";
    }

    @RequestMapping("/loginSuccess")
    public String loginSuccess(){
        return "pagehome/loginsuccess";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        String tel = (String) session.getAttribute("user");
        if (tel != "" && tel != null){
            //session.invalidate();清空session域所有session
            session.removeAttribute("user");
            return "pagehome/index";
        }
        return null;
    }

    @RequestMapping("/toRegister")
    public String toRegister(){
        return "pagehome/register";
    }

    @RequestMapping("/register")
    @ResponseBody
    public String register(User user){
        //System.out.println(user);
        user.setImage("http://localhost:8899/car-images/avatar.png");
        userMapper.addUser(user);
        Integer uid = user.getId();
        Score score = new Score();
        score.setUid(uid);
        score.setReward(0);
        scoreService.addScore(score);
        return "1";
    }

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "pagehome/login";
    }

    @RequestMapping("/refuse")
    public String refuse(){
        return "refuse";
    }

    @RequestMapping("/tomymain")
    public String mymain(HttpSession session,Integer id,Model model){
        String tel = (String) session.getAttribute("user");
        User user = userService.findByTelPwd(tel, "");
        Score score = scoreService.getScoreById(user.getId());
        model.addAttribute("score",score.getReward());
        if (id != null){
            model.addAttribute("backcarId", id);
        }else {
            BackCar backCar = new BackCar();
            backCar.setUid(user.getId());
            backCar.setStatus("租车中");
            BackCar backCar1 = backCarService.selectBackCar(backCar);
            //System.out.println("backcar="+backCar1);
            if (backCar1 == null){
                model.addAttribute("backcarId",0);
            }else {
                model.addAttribute("backcarId",backCar1.getId());
            }
        }
        return "mymain/mymain";
    }

    //前端跳转还车页面
    @RequestMapping("/tobackcar")
    public String backcar(HttpSession session,Model model){
        String tel = (String) session.getAttribute("user");
        User user = userService.findByTelPwd(tel, "");
        Score score = scoreService.getScoreById(user.getId());
        model.addAttribute("score",score.getReward());

        BackCar backCar = new BackCar();
        backCar.setUid(user.getId());
        backCar.setStatus("租车中");
        BackCar backCar1 = backCarService.getBackCar(backCar);
        //System.out.println("backcar="+backCar1);
        if (backCar1 == null){
            Car car = new Car();
            City city1 = new City();
            car.setName("无");
            city1.setName("无");
            backCar.setStatus("无");
            backCar.setOprice(0.0);
            backCar.setCar(car);
            backCar.setCity1(city1);
            backCar.setCity2(city1);
            model.addAttribute("backcar",backCar);
        }else {
            model.addAttribute("backcar",backCar1);
        }
        return "mymain/backcar";
    }

    @RequestMapping("/tomyinfo")
    public String myinfo(HttpSession session,Model model){
        String tel = (String) session.getAttribute("user");
        User user = userService.findByTelPwd(tel, "");
        Score score = scoreService.getScoreById(user.getId());
        model.addAttribute("score",score.getReward());
        return "mymain/myinfo";
    }

    private String tel;
    //个人资料显示
    @RequestMapping("/ajaxmyinfo")
    @ResponseBody
    public User ajaxmyinfo(HttpSession session,User user1){
        tel = (String) session.getAttribute("user");
        //System.out.println(user1);
        User user = null;
        if (tel!=null){
            user = userMapper.findByTelPwd(tel, "");
            return user;
        }
        return null;
    }

    //修改个人资料(手机号)
    @RequestMapping("/updatemyinfo")
    @ResponseBody
    public String updatemyinfo(User user){
        User u = userMapper.findByTelPwd(tel, "");
        user.setId(u.getId());
        //System.out.println(user);
        userMapper.updateUser(user);
        return "1";
    }
    /**
     * 修改昵称
     */
    @RequestMapping("/updatemynickname")
    @ResponseBody
    public String updatemynickname(HttpSession session,String nickname){
        tel= (String) session.getAttribute("user");
        if (tel != null){
            User user = new User();
            user.setTel(tel);
            user.setNickname(nickname);
            userService.updatenickname(user);
            return "1";
        }
        return "0";
    }

    /**
     * 修改生日
     */
    @RequestMapping("/updatemybirthday")
    @ResponseBody
    public String updatemybirthday(HttpSession session,String birthday){
        tel= (String) session.getAttribute("user");
        if (tel != null){
            User user = new User();
            user.setTel(tel);
            user.setBirthday(birthday);
            userService.updatebirthday(user);
        }
        return "1";
    }

    /**
     * 删除个人订单
     */
    @RequestMapping("/del")
    public String del(Integer id){
        //System.out.println(id);
        if (id!=null){
            userMapper.deleteOrderById(id);
            return "redirect:/user/tomymain";
        }
        return "error";
    }

    /**
     * 跳转到后台登录页面
     */
    @RequestMapping("/afterLogin")
    public String afterLogin(){
        return "after/login";
    }

    /**
     * 后台登录ajax验证
     */
    @RequestMapping(value = "/afterAjaxLogin")
    @ResponseBody
    public String afterAjaxLogin(User user, HttpSession session){
        if (user.getTel()!=null&&user.getPassword()!=null){
            UsernamePasswordToken token =
                    new UsernamePasswordToken(user.getTel(), user.getPassword());
            Subject subject = SecurityUtils.getSubject();
            try {
                subject.login(token);
                //System.out.println(subject.isAuthenticated());
                if (subject.isAuthenticated()){
                    session.setAttribute("user",user.getTel());
                }
                return "1";
            } catch (AuthenticationException e) {
                e.printStackTrace();
                return "/afterLogin";
            }
        }
        return "";
    }

    /**
     * 跳转到后台注册界面
     */
    @RequestMapping("/afterRegister")
    public String afterRegister(){
        return "after/register";
    }

    /**
     * 后台user注册ajax
     */
    @RequestMapping("/afterAjaxRegister")
    @ResponseBody
    public String afterAjaxRegister(User user){
        User u = userService.findByTelPwd(user.getTel(), user.getPassword());
        if (u == null){
            //默认图片
            user.setImage("http://localhost:8899/car-images/avatar.png");
            userService.addUser(user);
            //设置注册用户权限
            User user1 = userService.findByTelPwd(user.getTel(), user.getPassword());
            UserPermission userPermission = new UserPermission();
            userPermission.setUid(user1.getId());
            userPermission.setPermission("user:query");
            userPermissionMapper.addPerms(userPermission);
            userPermission.setPermission("user:update");
            userPermissionMapper.addPerms(userPermission);
            userPermission.setPermission("user:add");
            userPermissionMapper.addPerms(userPermission);
            return "1";
        }
        return "0";
    }

    /**
     * 后台登录成功跳转
     */
    @RequestMapping("/afterLoginSuccess")
    public String afterLoginSuccess(){
        return "redirect:/car/toCar";
    }

    //跳转后台汽车管理
    @RequestMapping("/toUser")
    public String toUser(){
        return "after/user";
    }

    /**
     * 后台用户分页查询
     */
    @RequestMapping("/allUser")
    @ResponseBody
    public List<Object> selectAllUser(Integer page){
        //System.out.println(page);
        ArrayList<Object> object = new ArrayList<>();
        List<User> list = userMapper.selectAllUser((page-1)*5);
        Integer dataCount = userMapper.countUser();
        object.add(list);
        object.add(dataCount);
        System.out.println("user="+list);
        return object;
    }

    @RequestMapping("/delUser")
    public String delUser(Integer id){
        if (id != null){
            userService.deleteUserById(id);
        }
        return "redirect:/user/toUser";
    }

    /**
     * 跳转到忘记密码页面
     */
    @RequestMapping("/toForgetPwd")
    public String toForgetPwd(String id, Model model){
        //前台id=before，后台id=after
        model.addAttribute("id",id);
        return "after/forgetpwd";
    }
    /**
     * 忘记密码
     */
    @RequestMapping("/forgetPwd")
    @ResponseBody
    public String forgetPwd(User user,String type){
        User u = userService.verifyEmail(user.getEmail());
        if (u != null){
            user.setId(u.getId());
            //type=before是前台的忘记密码,type=after是后台的
            if ("before".equals(type)){
                userService.updatePwd(user);
                return "2";
            }else if ("after".equals(type)){
                userService.updatePwd(user);
                return "1";
            }
        }
        return "0";
    }

    @RequestMapping("/afterLogout")
    public String afterLogout(){
        return "redirect:/user/afterLogin";
    }
}
