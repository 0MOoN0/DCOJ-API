package com.dcoj.entity;

import java.util.List;
import java.util.Map;

/**
 * 收藏题目实体类
 * @author WANGQING
 */
public class CollectProblemEntity {
    //收藏题目id
    private Integer cpid;
    //用户id
    private Integer uid;
    //收藏题目内容 Integer代表题目类型 0 选择 1 填空 2 判断 3 编程  List<Integer>代表某类题目的id号
    private Map<Integer, List<Integer>> contentMap;
}
