package com.customer.service;

import com.customer.exception.CustomerIdException;
import com.customer.model.dto.CustomerDTO;
import com.customer.model.entity.Customers;

import java.util.List;

public interface ICustomerService {
    List<Customers> getAllCustomers();

    Customers getCustomerById(final Integer id) throws CustomerIdException;

    Customers createCustomer(final CustomerDTO newCustomer);

    void deleteCustomer(final Integer id) throws CustomerIdException;
}
