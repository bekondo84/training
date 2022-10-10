package cm.pak.training;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
@ComponentScan(
        basePackages = "cm.pak.training.taskscheduler"
)
public class TrainingThreadPoolTaskScheduler {

    @Value("${spring.pool.size}")
    private int poolSize ;
    @Value("${spring.pool.name}")
    private String poolName ;

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(poolSize);
        threadPoolTaskScheduler.setThreadNamePrefix(poolName);
        return threadPoolTaskScheduler;
    }
}
