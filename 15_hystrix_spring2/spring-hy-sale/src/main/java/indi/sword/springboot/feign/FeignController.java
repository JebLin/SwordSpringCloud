package indi.sword.springboot.feign;

import com.netflix.hystrix.HystrixCircuitBreaker;
import com.netflix.hystrix.HystrixCommandKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignController {

    @Autowired
    private HelloClient helloClient;

    @RequestMapping(method = RequestMethod.GET, value = "/hello")
    public String hello() {
        return helloClient.hello();
    }

    /*
        因为这个方法超过了 yml设置的 500毫秒，所以会触发 fallback方法
     */
    @RequestMapping(method = RequestMethod.GET, value = "/toHello")
    public String toHello() {
        String result = helloClient.toHello();
        HystrixCircuitBreaker breaker = HystrixCircuitBreaker.Factory
                .getInstance(HystrixCommandKey.Factory
                        .asKey("HelloClient#toHello()"));
        System.out.println("断路器状态：" + breaker.isOpen());
        return result;
    }
}
