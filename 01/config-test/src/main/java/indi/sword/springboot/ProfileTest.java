package indi.sword.springboot;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class ProfileTest {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ProfileTest.class)
                .properties("spring.profiles.active=linux").run(args);
    }
}
