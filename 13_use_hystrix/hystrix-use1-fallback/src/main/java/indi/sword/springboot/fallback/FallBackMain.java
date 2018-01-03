package indi.sword.springboot.fallback;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

public class FallBackMain {
    public static void main(String[] args) {
        FallbackCommand fallbackCommand = new FallbackCommand();
        String result = fallbackCommand.execute();
        System.out.println(result);
    }
    static class FallbackCommand extends HystrixCommand<String> {
        public FallbackCommand(){
            super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"))
                    .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                            .withCircuitBreakerForceOpen(true))); // 强制打开断路器
        }

        @Override
        protected String run() throws Exception {
            return "success";
        }

        @Override
        protected String getFallback() {
            return "fallback";
        }
    }
}
