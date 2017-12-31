package indi.sword.springboot;

import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Configuration
public class MyConfig {

    @Autowired(required = false)
    @MyLoadBalanced
    private List<RestTemplate> restTemplateList = Collections.EMPTY_LIST; // 为这一堆restTemplate设置拦截器

    /**
     * @Decription 就是在这个拦截器方法里面，实现负载均衡的，我现在只是简单的做了一个跳转
     * @Author: rd_jianbin_lin
     * @Date : 2017/12/31 17:20
     */
    @Bean
    public SmartInitializingSingleton lbInitializing(){
        return new SmartInitializingSingleton() {
            @Override
            public void afterSingletonsInstantiated() {
                for (RestTemplate tpl : restTemplateList){
                    System.out.println("---- " + tpl);
                    List<ClientHttpRequestInterceptor> interceptors =
                            tpl.getInterceptors();
                    interceptors.add(new MyRedirectUrlInterceptor()); // 这里就可以加上负载均衡的拦截器
                    tpl.setInterceptors(interceptors);
                    interceptors.forEach(System.out::println);
                }
            }
        };
    }

}
