package com.powei.testmybatis.controller;

import com.powei.testmybatis.pojo.Admin;
import com.powei.testmybatis.pojo.Result;
import com.powei.testmybatis.service.impl.AdminService;
import com.sun.tools.jconsole.JConsoleContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.lang.model.util.Elements;
import java.util.List;

@RestController
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AdminController {
    @Autowired
    private AdminService adminService;


    @GetMapping("/admin/query")
    public Result getAdmins(@RequestHeader("token") String token){
        // 查询所有管理员
        try {
            List<Admin> admins = adminService.list(token);
            return Result.success(admins);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @DeleteMapping("/admin/delete/{id}")
    public Result deleteAdmin(@PathVariable("id") Integer id, @RequestHeader("token") String token) {
        if(id == 47){
            return Result.error("初始超级管理员不能删除");
        }
        try {
            adminService.delete(id, token);
            return Result.success(null);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @PostMapping("/admin/add")
    public Result addAdmin(@RequestBody Admin admin, @RequestHeader("token") String token){
        try {
            adminService.add(admin, token);
            return Result.success(null);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    //更新管理员权限组
    @PostMapping("/admin/update")
    public Result updateAdmin(@RequestBody Admin admin, @RequestHeader("token") String token){
        System.out.println("从网络得到的admin");
        System.out.println(admin.toString());
        try {
            adminService.update(admin, token);
            return Result.success(null);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @PostMapping("/admin/selfchange")
    public Result updateSelf(@RequestBody Admin admin, @RequestHeader("token") String token){
        try {
            System.out.println("从网络得到的admin");
            System.out.println(admin.toString());
            adminService.updateMyself(admin, token);
            return Result.success(null);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
}
