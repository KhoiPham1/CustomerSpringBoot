package com.codegym.Customer.service;

import com.codegym.Customer.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {
    Page<Customer> findAll(Pageable pageable);

    Customer findById(Long id);

    Page<Customer> findByNameContains(String name, Pageable pageable );

    void save(Customer customer);

    void delete(Long id);
}
