package indi.sword.springboot.log;

import feign.Feign;
import feign.Logger;
import indi.sword.springboot.HelloClient;

public class LogMain {
    public static void main(String[] args) {
        /**
         * NONE：默认值，不记录日志
         * BASIC：记录请求方法、URL、响应状态代码和执行时间
         * HEADERS：除BASIC记录的日志外，还会记录请求头和响应头的信息
         * FULLL：在HEADERS的基础上，请求和响应的元数据，都会保存
         */
        HelloClient client = Feign.builder()
                .logLevel(Logger.Level.FULL)
                .logger(new Logger.JavaLogger().appendToFile("logs/http.log"))
                .target(HelloClient.class,"http://localhost:8080");
        String result = client.hello();
        System.out.println(result);
    }
}
