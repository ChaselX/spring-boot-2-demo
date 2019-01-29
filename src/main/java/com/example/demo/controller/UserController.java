package com.example.demo.controller;

import com.example.demo.model.entity.SysUser;
import com.example.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author XieLongzhen
 * @date 2018/10/8 9:08
 */
@RestController
@RequestMapping("/users")
@Api("用户API")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    @ApiOperation(value = "查询用户列表", notes = "获取所有用户信息列表", response = ResponseEntity.class)
    public ResponseEntity getAllUsers() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(value = "添加用户", notes = "新增系统用户接口", response = ResponseEntity.class)
//    @ApiImplicitParam(name = "sysUser", value = "系统用户实体类", required = true, dataType = "SysUser")
    public ResponseEntity addUser(@RequestBody SysUser sysUser) {
        if (userService.addUser(sysUser)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("创建用户失败！");
    }
}
