package indi.sword.springboot;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard
public class DashboardApp {
	public static void main(String[] args) {
		new SpringApplicationBuilder(DashboardApp.class).web(true).run(args);
	}

}
