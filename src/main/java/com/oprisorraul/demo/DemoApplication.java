package com.oprisorraul.demo;

import com.oprisorraul.demo.model.Customer;
import com.oprisorraul.demo.repository.CustomerRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.oprisorraul.demo.repository")
@ComponentScan("com.oprisorraul.demo.controller")
public class DemoApplication {
	private final CustomerRepository customerRepository;

	public DemoApplication(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
