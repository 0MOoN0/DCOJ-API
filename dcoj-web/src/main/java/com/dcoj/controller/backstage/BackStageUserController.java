package com.dcoj.controller.backstage;


import com.dcoj.entity.ResponseEntity;
import com.dcoj.entity.RoleEntity;
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
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 后台用户管理 控制器
 *
 * @author  Jack Lin
 */
@RestController
@Validated
@Api(tags = "后台用户管理")
@RequestMapping(value = "/backStageUser", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class BackStageUserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @ApiOperation("获取所有用户")
    @ApiImplicitParam(name = "query", value = "查询关键字(用户名)", paramType = "query")
    @GetMapping("listAll")
    public ResponseEntity listAll(@RequestParam(name = "query", required = false) String query) {

        if(userService.listAll(query).size()<=0){
            return new ResponseEntity(400,"数据获取异常","");
        }else
        {
            return new ResponseEntity(userService.listAll(query));
        }

    }

    @ApiOperation("分页获取所有用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page_num", value = "页码", required = true, paramType = "query"),
            @ApiImplicitParam(name = "page_size", value = "每页显示数量", required = true, paramType = "query"),
            @ApiImplicitParam(name = "query", value = "查询关键字(用户名)", paramType = "query")
    })
    @GetMapping("listAllByPage")
    public ResponseEntity listAllByPage(@RequestParam(name = "page_num") int pageNum,
                                  @RequestParam(name = "page_size") int pageSize,
                                  @RequestParam(name = "query", required = false) String query) {
        // pageNum  页码
        // pageSize 每页显示数量
        Page pager = PageHelper.startPage(pageNum, pageSize);
        return new ResponseEntity(WebUtil.generatePageData(pager, userService.listAll(query)));
    }
    @ApiOperation("删除用户")
    @ApiImplicitParam(name = "user_id", value = "用户id")
    @DeleteMapping("/{user_id}")
    public ResponseEntity remove(@PathVariable("user_id") int userId) {
        userService.removeByPrimaryKey(userId);
        return new ResponseEntity("用户删除成功");
    }
    @ApiOperation("查看用户信息")
    @ApiImplicitParam(name = "user_id", value = "用户id")
    @GetMapping("information/{user_id}")
    public ResponseEntity information(@PathVariable("user_id") int userId) {
        UserEntity userEntity = userService.getByPrimaryKey(userId);
        RoleEntity roleEntity  = roleService.getRoleByUserId(userEntity.getUserId());
        WebUtil.assertNotNull(roleEntity, "获取用户角色信息错误");
        Map<String, Object> dataMap = new HashMap<>(3);
        dataMap.put("user", userEntity);
        dataMap.put("role",roleEntity);
        return new ResponseEntity(dataMap);
    }
    @ApiOperation("修改用户信息")
    @ApiImplicitParam(name = "userEntity", value = "用户信息")
    @PostMapping("update_information")
    public ResponseEntity updateInformation(@RequestBody UserEntity userEntity)
    {
        userService.updateUser(userEntity.getUserId(),userEntity);
        return new ResponseEntity("保存成功");
    }
    @ApiOperation("根据token获取用户信息")
    @ApiImplicitParam(name = "token", value = "账号token信息")
    @GetMapping("getByToken/{token}")
    public ResponseEntity getByToken(@PathVariable("token") String token)
    {
        UserEntity userEntity = userService.getByToken(token);
        WebUtil.assertNotNull(userEntity, "获取用户信息错误");
        RoleEntity roleEntity  = roleService.getRoleByUserId(userEntity.getUserId());
        WebUtil.assertNotNull(roleEntity, "获取用户角色信息错误");
        Map<String, Object> dataMap = new HashMap<>(3);
        dataMap.put("user", userEntity);
        dataMap.put("role",roleEntity);
        return new ResponseEntity(dataMap);
    }


}
