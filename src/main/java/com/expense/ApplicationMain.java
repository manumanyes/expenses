package com.expense;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages="com.expense")
public class ApplicationMain {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationMain.class);
	}
	

}
