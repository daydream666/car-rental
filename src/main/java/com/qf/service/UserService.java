package com.qf.service;

import com.qf.entity.User;

public interface UserService {

    public User findByTelPwd(String tel, String password);

    public void addUser(User user);

    //修改手机号
    public void updateUser(User user);

    //修改昵称
    public void updatenickname(User user);

    //修改生日
    public void updatebirthday(User user);

    public void deleteOrderById(Integer id);

    //后台删除用户
    public void deleteUserById(Integer id);

    //验证邮箱是否存在
    public User verifyEmail(String email);

    //修改密码
    public void updatePwd(User user);
}
