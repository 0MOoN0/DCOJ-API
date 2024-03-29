package com.dcoj.controller;

import com.dcoj.entity.ResponseEntity;
import com.dcoj.entity.UserEntity;
import com.dcoj.service.RoleService;
import com.dcoj.service.UserService;
import com.dcoj.util.WebUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户管理 控制器
 *
 * @author WANGQING
 */
@RestController
@Validated
@Api(tags = "用户管理")
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    /*
    @ApiOperation("跳转到用户管理界面")
    @RequiresPermissions("system:user:view")
    @GetMapping
    @ResponseBody
    public Object account() {
        System.out.println("system:user:view");
        // TODO: 2019.7.28 WANGQING 此处跳转到用户管理页面
        return "system:user:view";
    }
*/

    @ApiOperation("获取所有用户")
    @RequiresPermissions("system:user:list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page_num", value = "页码", required = true, paramType = "query"),
            @ApiImplicitParam(name = "page_size", value = "每页显示数量", required = true, paramType = "query"),
            @ApiImplicitParam(name = "query", value = "查询关键字(用户名)", paramType = "query")
    })
    @GetMapping
    public ResponseEntity listAll(@RequestParam(name = "page_num") int pageNum,
                                  @RequestParam(name = "page_size") int pageSize,
                                  @RequestParam(name = "query", required = false) String query,
                                  @RequestParam(name = "userRole", required = false) String userRole) {
        // pageNum  页码
        // pageSize 每页显示数量
        Map<String, Object> paramMap = new HashMap<>();
        if(StringUtils.isNotBlank(query)){
            paramMap.put("userName",query);
        }
        if(StringUtils.isNotBlank(userRole)){
            paramMap.put("userRole",userRole);
        }
        Page pager = PageHelper.startPage(pageNum, pageSize);
        return new ResponseEntity(WebUtil.generatePageData(pager, userService.listAll(paramMap)));
    }

    @ApiOperation("删除用户")
    @RequiresPermissions("system:user:remove")
    @ApiImplicitParam(name = "user_id", value = "用户id")
    @DeleteMapping("/{user_id}")
    public ResponseEntity remove(@PathVariable("user_id") int userId) {
        userService.removeByPrimaryKey(userId);
        return new ResponseEntity("用户删除成功");
    }

    @ApiOperation("修改单个用户信息")
    @RequiresPermissions("system:user:edit")
    @ApiImplicitParam(name = "user_id", value = "用户id")
    @GetMapping("/edit/{user_id}")
    public ResponseEntity edit(@PathVariable("user_id") int userId) {
        UserEntity userEntity = userService.getByPrimaryKey(userId);
        Map<String, Object> dataMap = new HashMap<>(2);
        dataMap.put("user", userEntity);
        return new ResponseEntity(dataMap);
    }

    @RequiresPermissions("system:user:edit")
    @ApiImplicitParam(name = "user_id", value = "用户id")
    @PutMapping("/edit/{user_id}")
    public ResponseEntity editSave(@PathVariable("user_id") int userId, UserEntity userEntity) {
        //UserEntity userEntity = userService.getByPrimaryKey(userId);
        Map<String, Object> dataMap = new HashMap<>(2);
        dataMap.put("user", userEntity);
        return new ResponseEntity(dataMap);
    }

    @ApiOperation("保存用户信息")
    @RequiresPermissions("system:user:save")
    @ApiImplicitParam(name = "user_id", value = "用户id")
    @PutMapping("/{user_id}")
    public ResponseEntity update(@PathVariable("user_id") int userId) {
        UserEntity userEntity = userService.getByPrimaryKey(userId);
        Map<String, Object> dataMap = new HashMap<>(2);
        dataMap.put("user", userEntity);
        return new ResponseEntity(dataMap);
    }


}
