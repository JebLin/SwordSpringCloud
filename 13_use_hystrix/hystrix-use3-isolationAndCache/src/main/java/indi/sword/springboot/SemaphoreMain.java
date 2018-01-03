package indi.sword.springboot;

import com.netflix.config.ConfigurationManager;
import com.netflix.hystrix.HystrixCommandProperties;

public class SemaphoreMain {
    public static void main(String[] args) throws InterruptedException {
        ConfigurationManager.getConfigInstance()
                .setProperty("hystrix.command.default.execution.isolation.strategy",
                        HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE);

        ConfigurationManager.getConfigInstance()
                .setProperty("hystrix.command.default.execution.isolation.semaphore.maxConcurrentRequests",4);

        for (int i = 0; i < 6; i++) {
            final int index = i;
            new Thread(() ->{
                MyCommand c = new MyCommand(index);
                c.execute();
            }).start();
        }
        Thread.sleep(5000);
    }

}
