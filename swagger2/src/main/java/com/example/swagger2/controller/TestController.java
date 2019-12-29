package com.example.swagger2.controller;

import com.example.swagger2.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "测试Controller")
@RestController
public class TestController {

    @GetMapping("/test")
    public String getTest(){
        return "getTest";
    }

    // 对于接口返回的实体类，会被Swagger扫描到
    @ApiOperation("获取用户")
    @GetMapping("/user")
    public User user(@ApiParam("用户名") String username){
        return new User();
    }

}
