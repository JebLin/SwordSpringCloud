package indi.sword.springboot;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import java.util.Scanner;

@SpringBootApplication
@EnableEurekaServer
public class ServerApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String profiles = scanner.next();
        new SpringApplicationBuilder(ServerApp.class).profiles(profiles).run(args);
    }
}
