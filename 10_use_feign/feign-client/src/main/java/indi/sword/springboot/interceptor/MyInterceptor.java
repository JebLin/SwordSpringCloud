package indi.sword.springboot.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class MyInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header("Content-type","application/json");
        System.out.println("这是自定义的拦截器。");
    }
}
