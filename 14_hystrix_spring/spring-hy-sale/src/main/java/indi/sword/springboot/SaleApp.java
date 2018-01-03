package indi.sword.springboot;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient   // Eureka
@EnableCircuitBreaker // breaker
@ServletComponentScan // 扫描 Filter Servlet
public class SaleApp {
    public static void main(String[] args) {
        new SpringApplicationBuilder(SaleApp.class).web(true).run(args);
    }
}
