package com.qf.mapper;

import com.qf.entity.Score;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreMapper {

    public void addScore(Score score);

    //获取积分
    public Score getScoreById(Integer uid);

    public void updateScoreByOrderStatus(Integer uid,Integer countOrderStatus);

    //后台积分管理分页
    public List<Score> selectAllScore(Integer page);
    public Integer countScore();

    //后台删除用户
    public void deleteScoreById(Integer id);
}
