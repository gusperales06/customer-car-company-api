package com.customer.service;

import com.customer.exception.CustomerIdException;
import com.customer.model.CustomerInfoDTO;
import com.customer.model.entity.Customers;

import java.util.List;

public interface ICustomerService {
    List<Customers> getAllCustomers();

    Customers getCustomerId(final Integer id) throws CustomerIdException;

    Customers createCustomer(final CustomerInfoDTO newCustomer);
}
