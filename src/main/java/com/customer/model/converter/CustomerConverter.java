package com.customer.model.converter;

import com.customer.model.dto.CustomerDTO;
import com.customer.model.entity.Customers;
import org.springframework.core.convert.converter.Converter;

public class CustomerConverter implements Converter<CustomerDTO, Customers> {
    @Override
    public Customers convert(CustomerDTO source) {
        var customer = new Customers(source.getFirstName(), source.getLastName(), source.getBirthdate());
        customer.setGender(source.getCustomersInfo().getGender());
        customer.setHouseholdIncome(source.getCustomersInfo().getHouseholdIncome());
        customer.setPhoneNumber(source.getCustomersInfo().getPhoneNumber());
        customer.setEmail(source.getCustomersInfo().getEmail());
        return customer;
    }
}
