package com.dcoj.controller.backstage;

import com.dcoj.entity.ResponseEntity;
import com.dcoj.entity.RoleEntity;
import com.dcoj.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 角色管理 控制器
 *
 * @author Jack Lin
 */
@RestController
@Validated
@Api(tags = "角色管理")
@RequestMapping(value = "/backStageRole", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class BackStageRoleController {

    @Autowired
    private RoleService roleService;

    @ApiOperation("获取所有角色")
    @GetMapping("/listAll")
    public ResponseEntity listAll(){
        List<RoleEntity> list = roleService.listAll();
        if(list == null || list.size() < 0){
            return new ResponseEntity(400,"暂无数据","");
        }else{
            return new ResponseEntity(list);
        }

    }
}
