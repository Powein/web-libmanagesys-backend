package com.powei.testmybatis.controller;

import com.powei.testmybatis.pojo.Chara;
import com.powei.testmybatis.pojo.Reader;
import com.powei.testmybatis.pojo.Result;
import com.powei.testmybatis.service.impl.ReaderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Slf4j
public class ReaderController {

    @Autowired
    private ReaderService readerService;

    @PostMapping("/reader/insert")
    public Result insertReader(@RequestBody Reader reader, @RequestHeader("token") String token) {
        try {
            readerService.addReader(reader, token);
            return Result.success(null);
        } catch (Exception e) {
            return Result.error("权限不足");
        }

    }

    @PostMapping("/reader/query")
    public Result queryReader(@RequestBody Reader reader, @RequestHeader("token") String token) {
        try {
            Reader newreader = readerService.queryReader(reader, token);
            return Result.success(newreader);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @GetMapping("/reader/delete/{id}")
    public Result deleteReader(@PathVariable("id") Integer readerId, @RequestHeader("token") String token) {
        try {
            readerService.deleteReader(readerId, token);
            return Result.success(null);
        } catch (Exception e) {
            return Result.error("权限不足");
        }
    }

    @PostMapping("/reader/update")
    public Result updateReader(@RequestBody Reader reader, @RequestHeader("token") String token) {
        try {
            readerService.updateReader(reader, token);
            return Result.success(null);
        } catch (Exception e) {
            return Result.error("权限不足");
        }
    }

    @PostMapping("/chara/list")
    public Result listChara (@RequestHeader("token") String token){
        try {
            List<Chara> charaList = readerService.queryChara(token);
            return Result.success(charaList);
        } catch (Exception e) {
            return Result.error("权限不足");
        }
    }

    @PostMapping("/chara/add")
    public Result addChara (@RequestBody Chara chara, @RequestHeader("token") String token){
        try {
            readerService.addChara(chara, token);
            return Result.success(null);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @PostMapping("/chara/update")
    public Result updateChara (@RequestBody Chara chara, @RequestHeader("token") String token){
        try {
            readerService.updateChara(chara, token);
            return Result.success(null);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    @PostMapping("/chara/delete/{id}")
    public Result deleteChara(@PathVariable("id") Integer id, @RequestHeader("token") String token) {
        try {
            readerService.deleteChara(id, token);
            return Result.success(null);
        } catch (Exception e) {
            return Result.error("权限不足");
        }
    }
}
