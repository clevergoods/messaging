package com.wf.messaging.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class ThreadConfig {

    @Value("#{new Integer('${process.thread.number}')}")
    private Integer threadNumber;

    @Bean
    public TaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(threadNumber);
        executor.setMaxPoolSize(threadNumber);
        executor.setThreadNamePrefix("messaging_executor_thread");
        executor.initialize();
        return executor;
    }
}
