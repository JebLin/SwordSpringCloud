package indi.sword.springboot;

import com.netflix.config.ConfigurationManager;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

/**
 * @Decription
 * @Author: rd_jianbin_lin
 * @Date : 2018/1/3 15:36
 */
/*
    https://github.com/netflix/Hystrix/wiki/Configuration#circuitBreaker.requestVolumeThreshold
    断路器开启
       1. 整个链路达到一定的阀值，默认情况下，10秒内产生超过20次请求，则符合第一个条件。
       2. 满足第一个条件的情况下，如果请求的错误百分比大于阀值，则会打开断路器，默认为50%。
 */
public class OpenMain {
    public static void main(String[] args) {
        // 10秒内大于10次请求
        ConfigurationManager.getConfigInstance()
                .setProperty("hystrix.command.default.circuitBreaker.requestVolumeThreshold",10);
        for (int i = 0; i < 15; i++) {
            ErrorCommand command = new ErrorCommand();
            command.execute();
            // 输出断路器状态
            System.out.println(i + ":" + command.isCircuitBreakerOpen()); // 那么这里肯定是执行10次失败之后，才把断路器breaker打开的
        }
    }
    static class ErrorCommand extends HystrixCommand<String> {

        public ErrorCommand(){
            super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"))
                    .andCommandPropertiesDefaults(HystrixCommandProperties
                            .Setter().withExecutionTimeoutInMilliseconds(500))); // 超时时间是500ms，超过说明有问题
        }
        @Override
        protected String run() throws Exception {
            Thread.sleep(800); // 8秒，肯定有问题
            return null;
        }
        @Override
        protected String getFallback(){
            return "fallback";
        }

    }
}
