package indi.sword.springboot;

import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;

public class MyConfig {

    @Bean
    public IRule getRule(){
        return new MyRule();
    }
}
