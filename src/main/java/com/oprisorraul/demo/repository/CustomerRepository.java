package com.oprisorraul.demo.repository;

import com.oprisorraul.demo.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}