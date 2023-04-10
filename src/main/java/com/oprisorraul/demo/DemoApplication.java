package com.oprisorraul.demo;

import jakarta.persistence.SynchronizationType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Scanner;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.oprisorraul.demo.repository")
@ComponentScan("com.oprisorraul.demo.configure")
@ComponentScan("com.oprisorraul.demo.controller")
public class DemoApplication {

	public DemoApplication() {
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
