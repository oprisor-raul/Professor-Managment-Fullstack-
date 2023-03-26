package com.oprisorraul.demo;

import com.oprisorraul.demo.repository.CustomerRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping
public class DemoApplication {
	private final CustomerRepository customerRepository;

	public DemoApplication(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		System.out.println("!.");
	}

	@GetMapping
	public List<Customer> getCustomers() {
		return customerRepository.findAll();
	}

	record NewCustomerRequest(
			String name,
			String email,
			Integer age
	) {}
	public void addCustomer(NewCustomerRequest request) {
		Customer customer = new Customer();
		customer.setName(request.name());
		customer.setEmail(request.email);
		customer.setAge(request.age);
		customerRepository.save(customer);
	}

}
