package com.customer.controller;

import com.customer.model.dto.CustomerDTO;
import com.customer.model.entity.Customers;
import com.customer.model.entity.CustomersInfo;
import com.customer.service.ICustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomersController.class)
class CustomersControllerTest {
    @MockBean
    private ICustomerService service;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objMapper;

    Customers dummyEntity;

    CustomerDTO dummyCustomer;

    @BeforeEach
    void setUp() {
        dummyEntity = new Customers("test", "test", LocalDate.of(1990, 2, 28), "9998887722");
        dummyEntity.setCustomerId(1);
        dummyEntity.setCustomersInfo();
        dummyEntity.getCustomersInfo()
                .setGender("Male");
        dummyEntity.getCustomersInfo()
                .setHouseholdIncome("1000");
        dummyEntity.getCustomersInfo()
                .setEmail("test@mail.com");

        dummyCustomer = new CustomerDTO("test", "test", LocalDate.of(1990, 2, 28), "9998887766")
                .customerInfo()
                .gender("Male")
                .householdIncome("1000")
                .email("test@mail.com")
                .build();

        objMapper = new ObjectMapper();
        objMapper.registerModule(new JavaTimeModule());
    }

    @Test
    void createCustomer() throws Exception {
        var c = objMapper.writeValueAsString(dummyCustomer);
        doReturn(dummyEntity).when(service).createCustomer(any(CustomerDTO.class));

        mockMvc.perform(post("/api/v1/customers/")
                        .contentType(APPLICATION_JSON_VALUE)
                        .accept(TEXT_PLAIN_VALUE)
                        .content(c))
                .andExpect(status().isCreated())
                .andExpect(redirectedUrl("http://localhost/api/v1/customers/id/1"));
    }

    @Test
    void getAllCustomers() throws Exception {
        when(service.getAllCustomers()).thenReturn(List.of(dummyEntity));

        var mvcResult = mockMvc.perform(get("/api/v1/customers/")
                        .contentType(APPLICATION_JSON_VALUE)
                        .accept(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
        var content = mvcResult.getResponse().getContentAsString();
        var collectionType = objMapper.getTypeFactory().constructCollectionType(List.class, Customers.class);
        List<Customers> result = objMapper.readValue(content, collectionType);

        assertThat(result, is(not(empty())));
        assertThat(result, hasItem(hasProperty("customerId", is(equalTo(1)))));
    }

    @Test
    void getCustomerById() throws Exception {
        when(service.getCustomerById(anyInt())).thenReturn(dummyEntity);

        var mvcResult = mockMvc.perform(get("/api/v1/customers/id/{id}", 1)
                        .contentType(APPLICATION_JSON_VALUE)
                        .accept(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
        var content = mvcResult.getResponse().getContentAsString();
        Customers result = objMapper.readValue(content, Customers.class);

        assertThat(result, hasProperty("customerId", is(equalTo(1))));
        assertThat(result, hasProperty("firstName", is(equalTo("test"))));
    }

    @Test
    void updateCustomerById() throws Exception {
        var c = objMapper.writeValueAsString(dummyCustomer);
        when(service.getCustomerById(anyInt())).thenReturn(dummyEntity);

        mockMvc.perform(put("/api/v1/customers/id/{id}", 1)
                        .contentType(APPLICATION_JSON_VALUE)
                        .accept(TEXT_PLAIN_VALUE)
                        .content(c))
                .andExpect(status().isAccepted());
    }

    @Test
    void deleteCustomer() throws Exception {
        doNothing().when(service).deleteCustomer(anyInt());

        mockMvc.perform(delete("/api/v1/customers/id/{id}", 1))
                .andExpect(status().isAccepted());
    }
}