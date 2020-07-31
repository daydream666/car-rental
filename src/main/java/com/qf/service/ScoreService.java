package com.qf.service;

import com.qf.entity.Score;

import java.util.List;

public interface ScoreService {

    public void addScore(Score score);

    //获取积分
    public Score getScoreById(Integer uid);

    public void updateScoreByOrderStatus(Integer uid,Integer countOrderStatus);

    public List<Score> selectAllScore(Integer page);
    public Integer countScore();

    public void deleteScoreById(Integer id);
}
