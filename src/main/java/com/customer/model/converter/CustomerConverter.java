package com.customer.model.converter;

import com.customer.model.CustomerInfoDTO;
import com.customer.model.entity.Customers;
import org.springframework.core.convert.converter.Converter;

public class CustomerConverter implements Converter<CustomerInfoDTO, Customers> {
    @Override
    public Customers convert(CustomerInfoDTO source) {
        var customer = new Customers(source.getFirstName(), source.getLastName(), source.getBirthdate());
        customer.setGender(source.getGender());
        customer.setHouseholdIncome(source.getHouseholdIncome());
        customer.setPhoneNumber(source.getPhoneNumber());
        customer.setEmail(source.getEmail());
        return customer;
    }
}
