package com.dcoj.controller.backstage.format;

import com.dcoj.entity.ProgramProblemEntity;
import com.dcoj.entity.ProgramTagEntity;

import java.util.List;
import java.util.Map;

//编程题包含tags的类
public class ProgramProblemWithTags {
    private ProgramProblemEntity programProblemEntity;

    public ProgramProblemEntity getProgramProblemEntity() {
        return programProblemEntity;
    }

    public void setProgramProblemEntity(ProgramProblemEntity programProblemEntity) {
        this.programProblemEntity = programProblemEntity;
    }

    private List<Map<String, Object>> listTags ;

    public List<Map<String, Object>> getListTags() {
        return listTags;
    }

    public void setListTags(List<Map<String, Object>> listTags) {
        this.listTags = listTags;
    }
}
