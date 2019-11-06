package org.willishz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

@SpringBootApplication
@EnableAsync
@EnableScheduling
public class PlayGroundApplication {

    private static final Logger log = LoggerFactory.getLogger(PlayGroundApplication.class);

    public static void main(String[] args) {
        log.info("Start PlayGroundApplication.. ");
        SpringApplication.run(PlayGroundApplication.class, args);
    }

    /**
     * 自定义异步线程池
     *
     * @return
     */
    @Bean
    public AsyncTaskExecutor taskExecutor(@Value("${taskExecutor.corePoolSize}") int corePoolSize,
                                          @Value("${taskExecutor.maxPoolSize}") int maxPoolSize,
                                          @Value("${taskExecutor.keepAliveSeconds}") int keepAliveSeconds,
                                          @Value("${taskExecutor.queueCapacity}") int queueCapacity,
                                          @Value("${taskExecutor.threadNamePrefix}") String threadNamePrefix) {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setThreadNamePrefix(threadNamePrefix);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setCorePoolSize(corePoolSize);
        executor.setKeepAliveSeconds(keepAliveSeconds);
        executor.setQueueCapacity(queueCapacity);

        // 设置拒绝策略
        executor.setRejectedExecutionHandler(new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                // .....
            }
        });
        // 使用预定义的异常处理类
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return executor;
    }
}
