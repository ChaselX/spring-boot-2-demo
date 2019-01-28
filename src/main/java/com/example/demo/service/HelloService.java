package com.example.demo.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

/**
 * @author XieLongzhen
 * @date 2019/1/28 11:03
 */
@Service
public class HelloService {

    @Async
    public Future<Boolean> testASync() {
        System.out.println("start");
        try {
            Thread.sleep(4000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("end");
        return AsyncResult.forValue(true);
    }
}
