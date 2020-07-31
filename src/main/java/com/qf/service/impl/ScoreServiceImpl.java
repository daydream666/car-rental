package com.qf.service.impl;

import com.qf.entity.Score;
import com.qf.mapper.ScoreMapper;
import com.qf.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private ScoreMapper scoreMapper;

    @Override
    public void addScore(Score score) {
        scoreMapper.addScore(score);
    }

    @Override
    public Score getScoreById(Integer uid) {
        return scoreMapper.getScoreById(uid);
    }

    @Override
    public void updateScoreByOrderStatus(Integer uid,Integer countOrderStatus) {
        scoreMapper.updateScoreByOrderStatus(uid,countOrderStatus);
    }

    @Override
    public List<Score> selectAllScore(Integer page) {
        return scoreMapper.selectAllScore(page);
    }

    @Override
    public Integer countScore() {
        return scoreMapper.countScore();
    }

    @Override
    public void deleteScoreById(Integer id) {
        scoreMapper.deleteScoreById(id);
    }
}
