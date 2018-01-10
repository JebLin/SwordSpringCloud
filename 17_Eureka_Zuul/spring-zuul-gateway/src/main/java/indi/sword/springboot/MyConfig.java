package indi.sword.springboot;

import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Decription 自定义路由规则，就是不要用 application.yml
 * @Author: rd_jianbin_lin
 * @Date : 2018/1/10 11:18
 */
//@Configuration
public class MyConfig {

    /**
     * 访问网关的 /sale/**，将会被路由到 spring-zuul-sale 服务进行处理
     */
    @Bean
    public PatternServiceRouteMapper patternServiceRouteMapper(){
        return new PatternServiceRouteMapper("(spring)-(zuul)-(?<module>.+)","${module}/**");
    }

}
/*
    去 application.yml 里面的配置给注释掉，然后就可以测试这个自定义路由规则了，一般比较困难的就需要

     ignoredPatterns: /sale/noRoute
      routes:
        sale:
          path: /sale/**  #也就表明了，全部请求都进入 9000 端口进行分发,推荐使用这一个 ,输入 http://localhost:9000/sale/food-sale/1
          serviceId: spring-zuul-sale
        spring-zuul-sale:  #这里相当于 serviceId 因为下面是path ，如果下面是 url，那么就是一个简单路由。
          path: /sale/**
        abc:            # 这里下面的url 既不是http开头又不是forward开头，那么就当成一个 serviceId
          path: /sale/**
          url: spring-zuul-sale
         # 上面三个配置，效果是一模一样的,都属于Ribbon 路由

 */
