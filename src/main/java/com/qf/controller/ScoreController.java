package com.qf.controller;

import com.qf.entity.Score;
import com.qf.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/score")
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    //跳转后台积分管理
    @RequestMapping("/toScore")
    public String toUser(){
        return "after/score";
    }
    /**
     * 后台用户分页查询
     */
    @RequestMapping("/allScore")
    @ResponseBody
    public List<Object> selectAllScore(Integer page){
        //System.out.println(page);
        ArrayList<Object> object = new ArrayList<>();
        List<Score> list = scoreService.selectAllScore((page-1)*5);
        Integer dataCount = scoreService.countScore();
        object.add(list);
        object.add(dataCount);
        System.out.println("list"+list);
        System.out.println("datacount"+dataCount);
        return object;
    }
    @RequestMapping("/delScore")
    public String delScore(Integer id){
        if (id != null){
            scoreService.deleteScoreById(id);
        }
        return "redirect:/score/toScore";
    }
}
