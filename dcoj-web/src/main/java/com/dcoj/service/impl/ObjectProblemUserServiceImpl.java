package com.dcoj.service.impl;

import com.dcoj.dao.ObjectProblemUserMapper;
import com.dcoj.entity.ObjectProblemUserEntity;
import com.dcoj.entity.example.ObjectProblemUserEntityExample;
import com.dcoj.service.ObjectProblemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Leon
 */
@Service
public class ObjectProblemUserServiceImpl implements ObjectProblemUserService {

    @Autowired
    private ObjectProblemUserMapper objectProblemUserMapper;

    /**
     * 接受一条数据，更新或插入用户客观题提交状态
     *
     * @param pid    题目ID
     * @param uid    用户ID
     * @param status 做题状态
     * @return
     */
    @Override
    public int insertOrUpdate(Integer pid, Integer uid, Integer status) {
        ObjectProblemUserEntity objectProblemUserEntity = new ObjectProblemUserEntity();
        objectProblemUserEntity.setPid(pid);
        objectProblemUserEntity.setUid(uid);
        objectProblemUserEntity.setStatus(status.byteValue());
        ObjectProblemUserEntityExample example = new ObjectProblemUserEntityExample();
        example.createCriteria().andPidEqualTo(objectProblemUserEntity.getPid())
                .andUidEqualTo(objectProblemUserEntity.getUid());
        List<ObjectProblemUserEntity> objectProblemUserEntities = objectProblemUserMapper.selectByExample(example);
        if(objectProblemUserEntities.size()>0){
            // 如果这道题已经回答过，则不会再更新状态
            if(objectProblemUserEntities.get(0).getStatus()==1){
                return 1;
            }
            // 更新数据
            return objectProblemUserMapper.updateByExampleSelective(objectProblemUserEntity, example);
        }
        return objectProblemUserMapper.insertSelective(objectProblemUserEntity);
    }
}
