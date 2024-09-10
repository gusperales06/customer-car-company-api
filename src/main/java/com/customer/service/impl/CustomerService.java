package com.customer.service.impl;

import com.customer.exception.CustomerIdException;
import com.customer.exception.NonCustomerChangesException;
import com.customer.model.converter.CustomerConverter;
import com.customer.model.dto.CustomerDTO;
import com.customer.model.entity.Customers;
import com.customer.repository.CustomerRepository;
import com.customer.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public Customers createCustomer(final CustomerDTO newCustomer) {
        var customerConverted = new CustomerConverter() //
                .convert(newCustomer);
        return customerRepository.saveAndFlush(Objects.requireNonNull(customerConverted));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Customers> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Customers getCustomerById(final Integer id) throws CustomerIdException {
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerIdException("The given Customer ID was not found"));
    }

    @Override
    @Transactional(rollbackFor = {CustomerIdException.class, NonCustomerChangesException.class})
    public void updateCustomerById(final Integer id, final CustomerDTO customerToUpdate) {
        var customer = this.getCustomerById(id);
        var customerUpdated = new CustomerConverter(id) //
                .convert(customerToUpdate);
        if (Objects.equals(customer, customerUpdated)) {
            throw new NonCustomerChangesException("The given Customer has no fields to update");
        }
        customerRepository.save(Objects.requireNonNull(customerUpdated));
    }

    @Override
    @Transactional(rollbackFor = CustomerIdException.class)
    public void deleteCustomer(final Integer id) throws CustomerIdException {
        if (!customerRepository.existsById(id)) {
            throw new CustomerIdException("The given Customer ID was not found");
        }
        customerRepository.deleteById(id);
    }
}
