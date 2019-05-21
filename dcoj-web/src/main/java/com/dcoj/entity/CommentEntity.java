package com.dcoj.entity;

import java.sql.Timestamp;
import java.util.List;

/**
 * 评论实体类
 *
 * @author WANGQING
 */
public class CommentEntity {
    //评论id
    private Integer cid;
    //评论内容
    private String content;
    //评论时间
    private Timestamp createTime;
    //点赞数
    private Integer likeNum;
    //是否删除评论（评论是否存在）
    private Boolean isDeleted;
    //评论是否显示
    private Boolean isShowed;
    //回复此评论的评论id集合
    private List<Integer> responseComment;
}
