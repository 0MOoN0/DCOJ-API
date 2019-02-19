package com.dcoj.judge.judger;


import com.dcoj.judge.entity.RequestEntity;
import com.dcoj.judge.entity.ResponseEntity;

/**
 * @author Smith
 **/
public interface JudgerApi {
    ResponseEntity judge(String url, RequestEntity requestEntity) throws Exception;
}
