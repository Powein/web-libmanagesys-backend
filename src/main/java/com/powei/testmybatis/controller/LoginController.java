package com.powei.testmybatis.controller;

import com.powei.testmybatis.pojo.Admin;
import com.powei.testmybatis.pojo.Result;
import com.powei.testmybatis.service.impl.AdminService;
import com.powei.testmybatis.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class LoginController {

    @Autowired
    AdminService adminService;
    @PostMapping("/login")
    public Result login(@RequestBody Admin admin) {
        log.info("管理员登录:{}", admin);
        Admin queryAdmin = adminService.getByUsernameAndPassword(admin);
        if (queryAdmin != null) {
            //登陆成功
            HashMap<String, Object> claims = new HashMap<>();
            claims.put("name", queryAdmin.getName());
            claims.put("id", queryAdmin.getId());
            String jwtStr = JwtUtils.generateJWT(claims);
            return Result.success(jwtStr);
        } else {
            //登陆失败
            return Result.error("用户名或密码错误");
        }
    }
}
