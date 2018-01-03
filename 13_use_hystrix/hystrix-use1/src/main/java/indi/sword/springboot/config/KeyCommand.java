package indi.sword.springboot.config;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixThreadPoolKey;

public class KeyCommand extends HystrixCommand<String>{

    public KeyCommand(){
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("TestGroupKey"))
                    .andCommandKey(HystrixCommandKey.Factory.asKey("commandKey"))
                    .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("PoolKey")));
    }

    @Override
    protected String run() throws Exception {
        return null;
    }
}
