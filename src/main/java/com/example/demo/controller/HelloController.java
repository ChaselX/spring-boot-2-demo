package com.example.demo.controller;

import com.example.demo.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author XieLongzhen
 * @date 2018/10/6 18:56
 */
@RestController
@RequestMapping("/hello")
public class HelloController {
    @Autowired
    private RedisTemplate<String, String> stringStringRedisTemplate;

    @Autowired
    private HelloService helloService;

    @GetMapping
    public String mainPage() {
        return "Hello SpringBoot From \"/\"";
    }

    @GetMapping("/sayHello")
    public String sayHello() {
        stringStringRedisTemplate.opsForValue().set("demo:SayHello", "Hello SpringBoot From Redis!", 5, TimeUnit.SECONDS);
        return stringStringRedisTemplate.opsForValue().get("demo:SayHello");
    }

    @GetMapping("/testAsync")
    public String testAsync() {
        System.out.println("handler request");
        try {
            Long start = System.currentTimeMillis();
            Future<Boolean> future = helloService.testASync();
            Thread.sleep(2000);
            if (future.get(5, TimeUnit.SECONDS)) {
                System.out.println("return");
                return "完成时间：" + System.currentTimeMillis() + " ，耗时：" + (System.currentTimeMillis() - start);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "未完成异步直接返回";
    }
}
