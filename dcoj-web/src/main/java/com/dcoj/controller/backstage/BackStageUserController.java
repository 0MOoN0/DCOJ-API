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
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
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
    @ApiImplicitParams({
        @ApiImplicitParam(name = "query", value = "查询关键字(用户名)", paramType = "String"),
        @ApiImplicitParam(name = "userRole", value = "用户角色", paramType = "String")
    })
    @GetMapping("listAll")
    public ResponseEntity listAll(@RequestParam(name = "query", required = false) String query,
                                  @RequestParam(name = "userRole", required = false) Integer userRole) {
        Map<String, Object> paramMap = new HashMap<>();
        if(StringUtils.isNotBlank(query)){
            paramMap.put("userName",query);
        }
        if(userRole != null && StringUtils.isNotBlank(userRole.toString())){
            paramMap.put("userRole",userRole);
        }
        List<UserEntity> userEntityList = userService.listAll(paramMap);
        if(userEntityList == null || userEntityList.size() == 0){
            return new ResponseEntity(400,"数据为空","");
        }
        return new ResponseEntity(userEntityList);
    }

    @ApiOperation("分页获取所有用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page_num", value = "页码", required = true, paramType = "Int"),
            @ApiImplicitParam(name = "page_size", value = "每页显示数量", required = true, paramType = "Int"),
            @ApiImplicitParam(name = "username", value = "查询关键字(用户名)", paramType = "String"),
            @ApiImplicitParam(name = "userRole", value = "用户角色", paramType = "String")
    })
    @GetMapping("listAllByPage")
    public ResponseEntity listAllByPage(@RequestParam(name = "page_num") int pageNum,
                                  @RequestParam(name = "page_size") int pageSize,
                                  @RequestParam(name = "username", required = false) String username,
                                  @RequestParam(name = "userRole", required = false) String userRole) {
        // pageNum  页码
        // pageSize 每页显示数量
        Map<String, Object> paramMap = new HashMap<>();
        if(StringUtils.isNotBlank(username)){
            paramMap.put("userName",username);
        }
        if(StringUtils.isNotBlank(userRole)){
            paramMap.put("userRole",userRole);
        }
        Page pager = PageHelper.startPage(pageNum, pageSize);
        List<UserEntity> userEntitylist = userService.listAll(paramMap);
        if(userEntitylist == null || userEntitylist.size() == 0 ){
            return new ResponseEntity(400,"暂无数据","");
        }
        return new ResponseEntity(WebUtil.generatePageData(pager,userEntitylist ));
    }

    @ApiOperation("新增用户")
    @ApiImplicitParam(name = "userEntity", value = "用户信息")
    @PostMapping("/{roleId}")
    public ResponseEntity addUser(@RequestBody UserEntity userEntity, @PathVariable("roleId") Integer roleId) throws Exception {
        RoleEntity roleEntity = roleService.getByPrimaryKey(roleId);
        if(roleEntity == null){
            return new ResponseEntity("角色不存在");
        }
        userService.addUserSelective(userEntity,roleId);
        return new ResponseEntity("用户新增成功");
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
    @GetMapping("/{user_id}")
    public ResponseEntity information(@PathVariable("user_id") int userId) {
        UserEntity userEntity = userService.getByPrimaryKey(userId);
        return new ResponseEntity(userEntity);
    }

    @ApiOperation("修改用户信息")
    @ApiImplicitParam(name = "userEntity", value = "用户信息")
    @PutMapping("/{roleId}")
    public ResponseEntity updateInformation(@RequestBody UserEntity userEntity, @PathVariable("roleId")  Integer roleId) throws Exception {
        RoleEntity roleEntity = roleService.getByPrimaryKey(roleId);
        if(roleEntity == null){
            return new ResponseEntity("角色不存在");
        }
        userEntity.setUserRole(roleEntity);
        userService.updateUserSelective(userEntity, roleEntity);
        return new ResponseEntity("保存成功");
    }

    @ApiOperation("根据token获取用户信息")
    @ApiImplicitParam(name = "token", value = "账号token信息")
    @GetMapping("/getByToken/{token}")
    public ResponseEntity getByToken(@PathVariable("token") String token)
    {
        UserEntity userEntity = userService.getByToken(token);
        WebUtil.assertNotNull(userEntity, "获取用户信息错误");
        return new ResponseEntity(userEntity);
    }

    @ApiOperation("更新用户信息")
    @ApiImplicitParam(name = "userEntity", value = "用户信息")
    @PutMapping("/updateUserInfo/{newPassword}")
    public ResponseEntity updateInformation(@RequestBody UserEntity userEntity,@PathVariable("newPassword") String newPassword) throws Exception {
        userService.updateUser(userEntity,newPassword);
        return new ResponseEntity("保存成功");
    }


}
