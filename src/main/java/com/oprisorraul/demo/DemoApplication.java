package com.oprisorraul.demo;

import com.oprisorraul.demo.model.Customer;
import com.oprisorraul.demo.repository.CustomerRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("api/customers")
public class DemoApplication {
	private final CustomerRepository customerRepository;

	public DemoApplication(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
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

	@PostMapping
	public void addCustomer(@RequestBody NewCustomerRequest request) {
		Customer customer = new Customer();
		customer.setName(request.name());
		customer.setEmail(request.email);
		customer.setAge(request.age);
		customerRepository.save(customer);
	}

	@DeleteMapping("{customerId}")
	public void deleteCustomer(@PathVariable("customerId") Integer id) {
		customerRepository.deleteById(id);
	}

	@PutMapping("{customerId}")
	public void updateCustomer(@PathVariable("customerId") Integer id, @RequestBody NewCustomerRequest request) {
		Customer customer = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
		customer.setName(request.name());
		customer.setEmail(request.email());
		customer.setAge(request.age());
		customerRepository.save(customer);
	}
}
