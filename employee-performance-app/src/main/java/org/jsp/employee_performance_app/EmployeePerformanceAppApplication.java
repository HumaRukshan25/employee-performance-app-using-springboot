package org.jsp.employee_performance_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "org.jsp.employee_performance_app")
public class EmployeePerformanceAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeePerformanceAppApplication.class, args);
	}

}
