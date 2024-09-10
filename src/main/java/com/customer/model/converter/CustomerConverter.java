package com.customer.model.converter;

import com.customer.model.dto.CustomerDTO;
import com.customer.model.entity.Customers;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CustomerConverter implements Converter<CustomerDTO, Customers> {
    private Integer customerId;
    private Customers customer;

    public CustomerConverter() {
    }

    public CustomerConverter(final Integer customerId) {
        this.customerId = customerId;
    }

    @Override
    public Customers convert(CustomerDTO source) {
        customer = new Customers(source.getFirstName(), source.getLastName(), source.getBirthdate());
        if (customerId != null) {
            customer.setCustomerId(this.customerId);
        }
        mapCustomerAdditionalInfo(source);
        return customer;
    }

    private void mapCustomerAdditionalInfo(CustomerDTO source) {
        if (!Objects.equals(source.getCustomersInfo(), null)) {
            customer.setGender(source.getCustomersInfo().getGender());
            customer.setHouseholdIncome(source.getCustomersInfo().getHouseholdIncome());
            customer.setPhoneNumber(source.getCustomersInfo().getPhoneNumber());
            customer.setEmail(source.getCustomersInfo().getEmail());
        }
    }
}
