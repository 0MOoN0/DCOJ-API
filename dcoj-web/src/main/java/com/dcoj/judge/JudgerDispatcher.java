package com.dcoj.judge;

import com.eagleoj.web.entity.JudgerEntity;
import com.eagleoj.web.service.JudgerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

/**
 * @author Smith
 **/
@Service
public class JudgerDispatcher {

    @Autowired
    private JudgerService judgerService;

    private List<JudgerEntity> judgerList;

    // 获取所有判卷机
    public void refresh() {
        judgerList = judgerService.listJudger();
    }

    // 获取判卷URL
    public String getJudgerUrl() {
        if (judgerList == null || judgerList.size() == 0) {
            refresh();
        }

        if (judgerList == null || judgerList.size() == 0) {
            return null;
        }

        return generateUrl(randomJudger());
    }

    // 随机指定判卷机
    private JudgerEntity randomJudger() {
        Random random = new Random();
        int size = judgerList.size();
        int offset = random.nextInt(size);
        return judgerList.get(offset);
    }

    // 根据判卷机实体类生成URL
    private String generateUrl(JudgerEntity entity) {
        return "http://"+entity.getUrl()+":"+entity.getPort();
    }
}
