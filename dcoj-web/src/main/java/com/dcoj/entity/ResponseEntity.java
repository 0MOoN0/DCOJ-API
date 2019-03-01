package com.dcoj.entity;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 通用响应类型，用于后端与前端数据交互
 * @author Leon
 **/
public class ResponseEntity {

    @JSONField(ordinal = 0)
    private Integer status;

    @JSONField(ordinal = 1)
    private String message;

    @JSONField(ordinal = 2)
    private Object data;

    public ResponseEntity() {
    }

    public ResponseEntity(Object data) {
        this(200, "success", data);
    }

    public ResponseEntity(String message) {
        this(200, message, null);
    }

    public ResponseEntity(String message, Object data) {
        this(200, message, data);
    }

    public ResponseEntity(Integer status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
