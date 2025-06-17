package com.techm.vsm.service;

import com.techm.vsm.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    Customer saveCustomer(Customer customer);

    Customer updateCustomer(Long id, Customer customer);

    void deleteCustomer(Long id);

    Optional<Customer> getCustomerById(Long id);

    List<Customer> getAllCustomers();
}
