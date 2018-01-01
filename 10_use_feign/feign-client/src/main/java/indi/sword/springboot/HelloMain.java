package indi.sword.springboot;

import feign.Feign;

public class HelloMain {
    public static void main(String[] args) {
        HelloClient client = Feign.builder().target(HelloClient.class,
                "http://localhost:8080");
        String result = client.hello();
        System.out.println(result);

    }
}
