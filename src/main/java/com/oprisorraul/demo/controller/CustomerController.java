//package com.oprisorraul.demo.controller;
//
//import com.oprisorraul.demo.model.Customer;
//import com.oprisorraul.demo.model.modelRequests.NewCustomerRequest;
//import com.oprisorraul.demo.repository.CustomerRepository;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("api/customers")
//public class CustomerController {
//    private final CustomerRepository customerRepository;
//
//    public CustomerController(CustomerRepository customerRepository) {
//        this.customerRepository = customerRepository;
//    }
//
//    @GetMapping
//    public List<Customer> getCustomers() {
//        return customerRepository.findAll();
//    }
//
//    @PostMapping
//    public void addCustomer(@RequestBody NewCustomerRequest request) {
//        Customer customer = new Customer();
//        customer.setName(request.name());
//        customer.setEmail(request.email());
//        customer.setAge(request.age());
//        customerRepository.save(customer);
//    }
//
//    @DeleteMapping("{customerId}")
//    public void deleteCustomer(@PathVariable("customerId") Integer id) {
//        customerRepository.deleteById(id);
//    }
//
//    @PutMapping("{customerId}")
//    public void updateCustomer(@PathVariable("customerId") Integer id, @RequestBody NewCustomerRequest request) {
//        Customer customer = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
//        customer.setName(request.name());
//        customer.setEmail(request.email());
//        customer.setAge(request.age());
//        customerRepository.save(customer);
//    }
//}
