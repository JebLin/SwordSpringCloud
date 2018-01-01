package indi.sword.springboot.interceptor;

import feign.Feign;
import indi.sword.springboot.HelloClient;

public class InterceptorMain {
    public static void main(String[] args) {
        HelloClient client = Feign.builder()
                .requestInterceptor(new MyInterceptor()) // 加上自定义的拦截器
                .target(HelloClient.class,"http://localhost:8080");
        String result = client.hello();
        System.out.println(result);
    }
}
