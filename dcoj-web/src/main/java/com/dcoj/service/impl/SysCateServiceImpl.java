package com.dcoj.service.impl;

import com.dcoj.dao.SysCateMapper;
import com.dcoj.entity.SysCate;
import com.dcoj.handler.ParamException;
import com.dcoj.param.CateParam;
import com.dcoj.service.SysCateService;
import com.dcoj.util.BeanValidator;
import com.dcoj.util.LevelUtil;
import com.dcoj.util.WebUtil;
import com.google.common.base.Preconditions;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author zxw
 * @Desriiption: 类别业务实现类
 */
@Service
public class SysCateServiceImpl implements SysCateService {

    @Resource
    private SysCateMapper sysCateMapper;

    /**
     *  保存类别
     * @param param 类别参数
     */
    public void save(CateParam param) {
        BeanValidator.check(param);
        if(checkExist(param.getParentId(), param.getName(), param.getId())) {
            throw new ParamException("同一层级下存在相同名称的类别");
        }
        SysCate cate = SysCate.builder().name(param.getName()).parentId(param.getParentId())
                .seq(param.getSeq()).remark(param.getRemark()).build();

        cate.setLevel(LevelUtil.calculateLevel(getLevel(param.getParentId()), param.getParentId()));
        cate.setOperator("system"); //TODO: 
        cate.setOperateIp("localhost");//TODO:
        cate.setOperateTime(new Date());
        sysCateMapper.insertSelective(cate);
    }

    /**
     *  删除类别
     * @param cateId
     */
    public void delete(int cateId) {
        SysCate cate = sysCateMapper.selectByPrimaryKey(cateId);
        Preconditions.checkNotNull(cate, "待删除的类别不存在，无法删除");
        if (sysCateMapper.countByParentId(cate.getId()) > 0) {
            throw new ParamException("当前类别下面有子类别，无法删除");
        }
        //TODO :判断类别下是否关联了题目
        sysCateMapper.deleteByPrimaryKey(cateId);
    }

    /**
     *  分页查询所有类别
     * @param query 查询条件
     * @return List<SysCate>
     */
    @Override
    public List<SysCate> listAllByPage(String query) {
        return sysCateMapper.getAllCate();
    }

    /**
     *  更新类别
     * @param sysCate
     */
    @Override
    public int updateSelective(SysCate sysCate) {
        return sysCateMapper.updateByPrimaryKeySelective(sysCate);
    }

    /**
     *  新增类别
     * @param sysCate
     */
    @Override
    public int saveSelective(SysCate sysCate) {
        WebUtil.assertNull(sysCateMapper.selectByPrimaryKey(sysCate.getId()), "该类别已存在，新增失败");
        boolean flag =  sysCateMapper.insertSelective(sysCate) == 1;
        WebUtil.assertIsSuccess(flag, "添加类别失败");
        return 1;
    }

    /**
     *  根据类别Id查询类别信息
     * @param sId
     */
    @Override
    public SysCate getById(Integer sId) {
        SysCate sysCate = sysCateMapper.selectByPrimaryKey(sId);
        WebUtil.assertNotNull(sysCate, "该类别不存在");
        return sysCate;

    }

    /**
     *  更新类别
     * @param param 类别参数
     */
    @Override
    public void update(CateParam param) {
        BeanValidator.check(param);
        if(checkExist(param.getParentId(), param.getName(), param.getId())) {
            throw new ParamException("同一层级下存在相同名称的类别");
        }
        SysCate before = sysCateMapper.selectByPrimaryKey(param.getId());
        Preconditions.checkNotNull(before, "待更新的类别不存在");
        if(checkExist(param.getParentId(), param.getName(), param.getId())) {
            throw new ParamException("同一层级下存在相同名称的类别");
        }

        SysCate after = SysCate.builder().id(param.getId()).name(param.getName()).parentId(param.getParentId())
                .seq(param.getSeq()).remark(param.getRemark()).build();
        after.setLevel(LevelUtil.calculateLevel(getLevel(param.getParentId()), param.getParentId()));
        after.setOperator("system-update"); //TODO:
        after.setOperateIp("localhost");//TODO:
        after.setOperateTime(new Date());

        //更新子类别
        updateWithChild(before, after);
    }

    /**
     *  更新当前类别的子类别
     * @param before 更新前
     * @param after 更新后
     */
    @Transactional
    void updateWithChild(SysCate before, SysCate after) {
        String newLevelPrefix = after.getLevel();
        String oldLevelPrefix = before.getLevel();
        if (!after.getLevel().equals(before.getLevel())) {
            //获取同一层级的子类别列表
            List<SysCate> cateList = sysCateMapper.getChildCateListByLevel(before.getLevel());
            if (CollectionUtils.isNotEmpty(cateList)) {
                for (SysCate cate : cateList) {
                    String level = cate.getLevel();
                    if (level.indexOf(oldLevelPrefix) == 0) {
                        level = newLevelPrefix + level.substring(oldLevelPrefix.length());
                        cate.setLevel(level);
                    }
                }
                //批量更新
                sysCateMapper.batchUpdateLevel(cateList);
            }
        }
        sysCateMapper.updateByPrimaryKey(after);
    }

    /**
     *  查重
     * @param parentId 上级ID
     * @param cateName 类别名称
     * @param cateId 类别Id
     * @return
     */
    private boolean checkExist(Integer parentId, String cateName, Integer cateId) {
        //大于已经存在
        return sysCateMapper.countByNameAndParentId(parentId, cateName, cateId) > 0;
    }

    /**
     *  获取层级
     * @param cateId 类别ID
     * @return
     */
    private String getLevel(Integer cateId) {
        SysCate cate = sysCateMapper.selectByPrimaryKey(cateId);
        if (cate == null) {
            return null;
        }
        return cate.getLevel();
    }

}
