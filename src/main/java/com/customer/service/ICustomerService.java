package com.customer.service;

import com.customer.exception.CustomerIdException;
import com.customer.model.dto.CustomerDTO;
import com.customer.model.entity.Customers;

import java.util.List;

public interface ICustomerService {
    Customers createCustomer(final CustomerDTO newCustomer);

    List<Customers> getAllCustomers();

    Customers getCustomerById(final Integer id) throws CustomerIdException;

    void updateCustomerById(final Integer id, final CustomerDTO customerToUpdate);

    void deleteCustomer(final Integer id) throws CustomerIdException;
}
