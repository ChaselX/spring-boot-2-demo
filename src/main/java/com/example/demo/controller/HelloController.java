package com.example.demo.controller;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author XieLongzhen
 * @date 2018/10/6 18:56
 */
@RestController
@RequestMapping("/")
public class HelloController {
    @Autowired
    private RedisTemplate<String, String> stringStringRedisTemplate;

    @GetMapping
    public String mainPage() {
        return "Hello SpringBoot From \"/\"";
    }

    @GetMapping("/sayHello")
    public String sayHello() {
        stringStringRedisTemplate.opsForValue().set("demo:SayHello", "Hello SpringBoot From Redis!", 5, TimeUnit.SECONDS);
        return stringStringRedisTemplate.opsForValue().get("demo:SayHello");
    }
}
