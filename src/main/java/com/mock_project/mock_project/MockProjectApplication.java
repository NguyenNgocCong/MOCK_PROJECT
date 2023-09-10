package com.mock_project.mock_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.mock_project.mock_project")
public class MockProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(MockProjectApplication.class, args);
	}

}
