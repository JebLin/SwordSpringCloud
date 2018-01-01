package indi.sword.springboot.httpClient;

import feign.Feign;
import indi.sword.springboot.HelloClient;

public class MyClientTest {
    public static void main(String[] args) {
        HelloClient client = Feign.builder()
                .client(new MyClient()) // 使用自定义的 Client  默认使用HttpURLConnection连接HTTP服务
                .target(HelloClient.class,"http://localhost:8080");
        String result = client.hello();
        System.out.println(result);
    }
}
