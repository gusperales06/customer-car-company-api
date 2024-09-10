package com.customer.service.impl;

import com.customer.exception.CustomerIdException;
import com.customer.exception.NonCustomerChangesException;
import com.customer.model.converter.CustomerConverter;
import com.customer.model.dto.CustomerDTO;
import com.customer.model.entity.Customers;
import com.customer.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.core.IsNot.not;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {
    private Customers dummyEntity;

    private CustomerDTO dummyCustomer;

    @Mock
    private CustomerRepository repository;

    @Spy
    private CustomerConverter converter;

    @InjectMocks
    private CustomerService service;

    @BeforeEach
    void setUp() {
        dummyEntity = new Customers("test", "test", LocalDate.of(1990, 2, 28));
        dummyEntity.setCustomerId(1);
        dummyEntity.setGender("Male");
        dummyEntity.setHouseholdIncome("1000");
        dummyEntity.setPhoneNumber("9998887766");
        dummyEntity.setEmail("test@mail.com");

        dummyCustomer = new CustomerDTO("test", "test", LocalDate.of(1990, 2, 28))
                .customerInfo()
                .gender("Male")
                .householdIncome("1000")
                .phoneNumber("9998887766")
                .email("test@mail.com")
                .build();
    }

    @Test
    void createCustomer() {
        when(repository.saveAndFlush(any(Customers.class))).thenReturn(dummyEntity);

        var result = service.createCustomer(dummyCustomer);

        verify(repository, times(1)).saveAndFlush(any(Customers.class));
        assertThat(result, hasProperty("customerId", is(equalTo(1))));
        assertThat(result, hasProperty("firstName", is(equalTo("test"))));
        assertThat(result, hasProperty("lastName", is(equalTo("test"))));
        assertThat(result, hasProperty("birthdate", is(equalTo(LocalDate.of(1990, 2, 28)))));
    }

    @Test
    void getAllCustomers() {
        when(repository.findAll()).thenReturn(List.of(dummyEntity));

        var result = service.getAllCustomers();

        verify(repository, times(1)).findAll();
        assertThat(result, is(not(empty())));
    }

    @Test
    void getCustomerById() {
        when(repository.findById(anyInt())).thenReturn(Optional.ofNullable(dummyEntity));

        var result = service.getCustomerById(anyInt());

        verify(repository, times(1)).findById(anyInt());
        assertThat(result, hasProperty("customerId", is(equalTo(1))));
        assertThat(result, hasProperty("firstName", is(equalTo("test"))));
        assertThat(result, hasProperty("lastName", is(equalTo("test"))));
        assertThat(result, hasProperty("birthdate", is(equalTo(LocalDate.of(1990, 2, 28)))));

    }

    @Test
    void updateCustomerById() {
        when(repository.findById(anyInt())).thenReturn(Optional.ofNullable(dummyEntity));
        when(repository.save(any(Customers.class))).thenReturn(dummyEntity);
        dummyCustomer.getCustomersInfo() //
                .setEmail("test1@mail.com");

        service.updateCustomerById(anyInt(), dummyCustomer);

        verify(repository, times(1)).findById(anyInt());
        verify(repository, times(1)).save(any(Customers.class));
    }

    @Test
    void updateCustomerById_thenThrowException() {
        when(repository.findById(anyInt())).thenReturn(Optional.ofNullable(dummyEntity));

        assertThrows(NonCustomerChangesException.class, () -> service.updateCustomerById(anyInt(), dummyCustomer));
    }

    @Test
    void deleteCustomer() {
        when(repository.existsById(anyInt())).thenReturn(true);
        doNothing().when(repository).deleteById(anyInt());

        service.deleteCustomer(anyInt());

        verify(repository, times(1)).existsById(anyInt());
        verify(repository, times(1)).deleteById(anyInt());

    }

    @Test
    void deleteCustomer_thenThrowException() {
        when(repository.existsById(anyInt())).thenReturn(false);

        assertThrows(CustomerIdException.class ,() -> service.deleteCustomer(anyInt()));

        verify(repository, times(1)).existsById(anyInt());

    }
}