package indi.sword.springboot;

import com.netflix.config.ConfigurationManager;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandMetrics;
import com.netflix.hystrix.HystrixCommandProperties;

public class CloseMain {
    public static void main(String[] args) throws InterruptedException {
        ConfigurationManager.getConfigInstance()
                .setProperty("hystrix.command.default.circuitBreaker.requestVolumeThreshold",3);
        boolean isTimeout = true;
        for (int i = 0; i < 10; i++) {
            Thread.sleep(50);
            TestCommand c = new TestCommand(isTimeout);
            c.execute();
            HystrixCommandMetrics.HealthCounts hc = c.getMetrics().getHealthCounts();
            System.out.println("断路器状态：" + c.isCircuitBreakerOpen() +
                    ",请求数量：" + hc.getTotalRequests());
            if(c.isCircuitBreakerOpen()){
                isTimeout = false;
                System.out.println("============= 断路器打开了，等待休眠期结束。");
                Thread.sleep(6000);
            }

        }

    }
    static class TestCommand extends HystrixCommand<String>{

        private boolean isTimeout;
        public TestCommand(boolean isTimeout) {
            super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"))
                    .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                    .withExecutionTimeoutInMilliseconds(500)));
            this.isTimeout = isTimeout;
        }

        @Override
        protected String run() throws Exception {
            if(isTimeout){
                Thread.sleep(800);
            }else{
                Thread.sleep(100);
            }
            return "";
        }

        @Override
        protected String getFallback(){
            return "fallback";
        }
    }
}
