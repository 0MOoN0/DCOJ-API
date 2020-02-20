package com.dcoj.judge;


import org.springframework.stereotype.Service;

/**
 * @author Smith
 **/
@Service
public class JudgerDispatcher {

    // 获取判卷URL
    public String getJudgerUrl() {
        return "http://47.115.54.133:5000";
    }


/*    @Autowired
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
    }*/
}
