package com.customer.service.impl;

import com.customer.exception.CustomerIdException;
import com.customer.model.CustomerInfoDTO;
import com.customer.model.converter.CustomerConverter;
import com.customer.model.entity.Customers;
import com.customer.repository.CustomerRepository;
import com.customer.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CustomerService implements ICustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @Override
    public List<Customers> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customers getCustomerId(final Integer id) throws CustomerIdException {
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerIdException("The given Customer ID was not found"));
    }

    @Override
    public Customers createCustomer(final CustomerInfoDTO newCustomer) {
        var customerConverter = new CustomerConverter();
        var lCustomer = customerConverter.convert(newCustomer);
        return customerRepository.saveAndFlush(Objects.requireNonNull(lCustomer));
    }
}
