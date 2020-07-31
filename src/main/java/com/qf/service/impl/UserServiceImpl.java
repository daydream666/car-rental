package com.qf.service.impl;

import com.qf.entity.User;
import com.qf.mapper.UserMapper;
import com.qf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByTelPwd(String tel, String password) {
        return userMapper.findByTelPwd(tel, password);
    }

    @Override
    public void addUser(User user) {
        userMapper.addUser(user);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public void updatenickname(User user) {
        userMapper.updatenickname(user);
    }

    @Override
    public void updatebirthday(User user) {
        userMapper.updatebirthday(user);
    }

    @Override
    public void deleteOrderById(Integer id) {
        userMapper.deleteOrderById(id);
    }

    @Override
    public void deleteUserById(Integer id) {
        userMapper.deleteUserById(id);
    }

    @Override
    public User verifyEmail(String email) {
        return userMapper.verifyEmail(email);
    }

    @Override
    public void updatePwd(User user) {
        userMapper.updatePwd(user);
    }
}
