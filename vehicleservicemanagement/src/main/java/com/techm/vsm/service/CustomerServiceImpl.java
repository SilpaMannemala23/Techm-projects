package com.techm.vsm.service;

import com.techm.vsm.model.Customer;
import com.techm.vsm.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Long id, Customer customer) {
        Optional<Customer> existing = customerRepository.findById(id);
        if(existing.isPresent()) {
            Customer c = existing.get();
            c.setName(customer.getName());
            c.setEmail(customer.getEmail());
            c.setPhone(customer.getPhone());
            c.setAddress(customer.getAddress());
            return customerRepository.save(c);
        } else {
            throw new RuntimeException("Customer not found with id " + id);
        }
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
}
