package com.powei.testmybatis.controller;

import com.powei.testmybatis.pojo.PurView;
import com.powei.testmybatis.pojo.Result;
import com.powei.testmybatis.service.impl.PurViewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Slf4j
public class PurViewController {
    @Autowired
    private PurViewService purViewService;

    @GetMapping("/authority/queryall")
    public Result getAuthority(@RequestHeader("token") String token){
        try {
            List<PurView> purViews = purViewService.queryAll(token);
            return Result.success(purViews);
        } catch (Exception e){
            return Result.error(e.getMessage());
        }
    }

    @PostMapping("/authority/add")
    public Result addAuthority(@RequestBody PurView purView, @RequestHeader("token")String token){
        try {
            purViewService.add(purView, token);
            return Result.success(null);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @GetMapping("/authority/delete/{id}")
    public Result deleteAuthority(@PathVariable("id")Integer id, @RequestHeader("token")String token){
        if(id == 1){
            return Result.error("不能删除超级管理员");
        }
        System.out.println("前端传来的将要删除的ID是");
        System.out.println(id);
        try {
           purViewService.delete(id, token);
            return Result.success(null);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @PostMapping("/authority/update")
    public Result updateAuthority(@RequestBody PurView purView,@RequestHeader("token")String token) {
        try {
            purViewService.update(purView, token);
            return Result.success(null);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
}
