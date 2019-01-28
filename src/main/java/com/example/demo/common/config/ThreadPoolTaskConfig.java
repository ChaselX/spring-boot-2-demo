package com.example.demo.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author XieLongzhen
 * @date 2019/1/28 16:27
 */
@Configuration
public class ThreadPoolTaskConfig {
    @Autowired
    public ThreadPoolTaskConfig(ThreadPoolTaskExecutor executor) {
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.setWaitForTasksToCompleteOnShutdown(true);
    }
}
