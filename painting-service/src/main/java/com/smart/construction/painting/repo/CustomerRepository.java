package com.smart.construction.painting.repo;

import java.util.List;

import com.smart.construction.painting.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
	List<Customer> findByLastName(String lastName);
}