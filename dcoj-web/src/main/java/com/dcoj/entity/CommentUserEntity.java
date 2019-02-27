package com.dcoj.entity;

import java.util.List;

/**
 * 评论由哪个用户发布 用户一对多条评论 实体类
 * @author WANGQING
 */
public class CommentUserEntity {
    //用户id
    private Integer uid;
    //某用户所评论id的集合
    private List<Integer> cids;
}
