package indi.sword.springboot;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class InvokeApp {
    public static void main(String[] args) {
        new SpringApplicationBuilder(InvokeApp.class).web(true).run(args);
    }
}
