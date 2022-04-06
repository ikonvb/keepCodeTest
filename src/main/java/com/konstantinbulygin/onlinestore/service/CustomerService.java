package com.konstantinbulygin.onlinestore.service;

import com.konstantinbulygin.onlinestore.model.Customer;
import com.konstantinbulygin.onlinestore.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer deleteByCustomerId(int id) {
        return customerRepository.deleteByCustomerId(id);
    }

    public Optional<Customer> findById(int id) {
        return customerRepository.findById(id);
    }
}
