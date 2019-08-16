package com.dcoj.service.impl;

import com.dcoj.dao.SysCateMapper;
import com.dcoj.dto.CateLevelDto;
import com.dcoj.entity.SysCate;
import com.dcoj.service.SysTreeService;
import com.dcoj.util.LevelUtil;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author zxw
 * @Desriiption: 计算树业务实现类
 */
@Service
public class SysTreeServiceImpl implements SysTreeService {

    @Resource
    private SysCateMapper sysCateMapper;

    /**
     *  类别树
     * @return
     */
    @Override
    public List<CateLevelDto> cateTree() {
        List<SysCate> cateList = sysCateMapper.getAllCate();

        List<CateLevelDto> dtoList = Lists.newArrayList();
        for (SysCate cate : cateList) {
            CateLevelDto dto = CateLevelDto.adapt(cate);
            dtoList.add(dto);
        }
        return cateListToTree(dtoList);
    }

    /**
     *  类别列表转成树形结构
     * @param cateLevelList 类别层级列表
     * @return
     */
    public List<CateLevelDto> cateListToTree(List<CateLevelDto> cateLevelList) {
        if (CollectionUtils.isEmpty(cateLevelList)) {
            return Lists.newArrayList();
        }
        // level -> [cate1, cate2, ...] Map<String, List<Object>>
        Multimap<String, CateLevelDto> levelCateMap = ArrayListMultimap.create();
        //1级类别
        List<CateLevelDto> rootList = Lists.newArrayList();

        for (CateLevelDto dto : cateLevelList) {
            levelCateMap.put(dto.getLevel(), dto);
            if (LevelUtil.ROOT.equals(dto.getLevel())) {
                rootList.add(dto);
            }
        }
        // 按照seq从小到大排序
        Collections.sort(rootList, new Comparator<CateLevelDto>() {
            public int compare(CateLevelDto o1, CateLevelDto o2) {
                return o1.getSeq() - o2.getSeq();
            }
        });
        // 递归生成树
        transformCateTree(rootList, LevelUtil.ROOT, levelCateMap);
        return rootList;
    }

    // level:0, 0, all 0->0.1,0.2
    // level:0.1
    // level:0.2
    public void transformCateTree(List<CateLevelDto> cateLevelList, String level, Multimap<String, CateLevelDto> levelCateMap) {
        for (int i = 0; i < cateLevelList.size(); i++) {
            // 遍历该层的每个元素
            CateLevelDto cateLevelDto = cateLevelList.get(i);
            // 处理当前层级的数据
            String nextLevel = LevelUtil.calculateLevel(level, cateLevelDto.getId());
            // 处理下一层
            List<CateLevelDto> tempCateList = (List<CateLevelDto>) levelCateMap.get(nextLevel);
            if (CollectionUtils.isNotEmpty(tempCateList)) {
                // 排序
                Collections.sort(tempCateList, cateSeqComparator);
                // 设置下一层部门
                cateLevelDto.setCateList(tempCateList);
                // 进入到下一层处理
                transformCateTree(tempCateList, nextLevel, levelCateMap);
            }
        }
    }

    /**
     *  类别排序比较器
     */
    public Comparator<CateLevelDto> cateSeqComparator = new Comparator<CateLevelDto>() {
        public int compare(CateLevelDto o1, CateLevelDto o2) {
            return o1.getSeq() - o2.getSeq();
        }
    };
}
