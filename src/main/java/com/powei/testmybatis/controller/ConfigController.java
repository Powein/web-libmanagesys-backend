package com.powei.testmybatis.controller;

import com.powei.testmybatis.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@Slf4j
public class ConfigController {
    @RequestMapping("/config")
    public Result list(){
        System.out.println("Hello world");
        log.info("测试设置页面");
        return new Result(200,"success", null);
    }
}
