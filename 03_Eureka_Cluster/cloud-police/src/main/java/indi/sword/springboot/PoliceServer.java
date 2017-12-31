package indi.sword.springboot;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import java.util.Scanner;

@SpringBootApplication
@EnableEurekaClient
public class PoliceServer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 读取控制台的端口输入
        String port = scanner.nextLine();
        new SpringApplicationBuilder(PoliceServer.class).properties("server.port=" + port).run(args);
    }
}
