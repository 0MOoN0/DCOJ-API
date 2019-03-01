package com.dcoj.service;

import com.dcoj.entity.TagProblemEntity;

import java.util.List;

/**
 * @author WANGQING
 * @description
 */
public interface TagProblemService {
    void saveProblemTag(int tid, int pid);

    void removeProblemTags(int pid);

    int countTagProblems(int tid);

    List<TagProblemEntity> getProblemTags(int pid);
}
