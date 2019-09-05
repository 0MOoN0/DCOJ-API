package com.dcoj.controller;

import com.dcoj.dto.CateLevelDto;
import com.dcoj.entity.ResponseEntity;
import com.dcoj.param.CateParam;
import com.dcoj.service.SysCateService;
import com.dcoj.service.SysTreeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @author zxw
 * @Desriiption: 类别控制类
 */
@Controller
@RequestMapping(value = "/sys/cate")
@Slf4j
public class SysCateController {
    @Autowired
    private SysCateService sysCateService;
    @Autowired
    private SysTreeService sysTreeService;

    /**
     *  页面跳转
     * @param paramMap 测试参数
     * @return
     */
    @RequestMapping("/cate.page")
    public String page(Map<String, Object> paramMap) {
        paramMap.put("name", "张三");
        paramMap.put("age", 35);
        return "cate";
    }

    /**
     *  新增类别
     * @param param 类别参数
     * @return
     */
    @RequestMapping("/save.json")
    @ResponseBody
    public ResponseEntity saveCate(CateParam param) {
        sysCateService.save(param);
        return new ResponseEntity("新增类别成功~~");
    }

    @RequestMapping("/tree.json")
    @ResponseBody
    public ResponseEntity tree() {
        List<CateLevelDto> dtoList = sysTreeService.cateTree();
        return new ResponseEntity(dtoList);
    }

    /**
     *  更新类别
     * @param param 类别参数
     * @return
     */
    @RequestMapping("/update.json")
    @ResponseBody
    public ResponseEntity updateCate(CateParam param) {
        sysCateService.update(param);
        return new ResponseEntity("更新类别成功");
    }

    /**
     *  删除类别
     * @param id
     * @return
     */
    @RequestMapping("/delete.json")
    @ResponseBody
    public ResponseEntity delete(@RequestParam("id") int id) {
        sysCateService.delete(id);
        return new ResponseEntity("删除类别成功");
    }
}
