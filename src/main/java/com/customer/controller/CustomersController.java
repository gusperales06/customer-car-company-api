package com.customer.controller;

import com.customer.exception.CustomerIdException;
import com.customer.model.dto.CustomerDTO;
import com.customer.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

@RestController
@RequestMapping(path = "/api/v1/customer")
public class CustomersController {
    public static final String ID_URI = "/id/{id}";
    private final ICustomerService customerService;

    @Autowired
    public CustomersController(ICustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = TEXT_PLAIN_VALUE)
    public ResponseEntity<?> createCustomer(@Valid @RequestBody final CustomerDTO newCustomer) {
        var customerCreated = customerService.createCustomer(newCustomer);
        var newURL = ServletUriComponentsBuilder.fromCurrentRequest() //
                .path(ID_URI)
                .buildAndExpand(customerCreated.getCustomerId())
                .toUri();
        return ResponseEntity.created(newURL) //
                .build();
    }

    @GetMapping(path = "/all", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllCustomers() {
        var response = customerService.getAllCustomers();
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = ID_URI, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCustomerById(@PathVariable(name = "id") final Integer id) throws CustomerIdException {
        var response = customerService.getCustomerById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping(path = ID_URI, consumes = APPLICATION_JSON_VALUE, produces = TEXT_PLAIN_VALUE)
    public ResponseEntity<?> updateCustomerById(@PathVariable(name = "id") final Integer id,
                                                @Valid @RequestBody final CustomerDTO updateCustomer) throws CustomerIdException {
        customerService.updateCustomerById(id, updateCustomer);
        return ResponseEntity.accepted()
                .build();
    }

    @DeleteMapping(path = ID_URI)
    public ResponseEntity<?> deleteCustomer(@PathVariable(name = "id") final Integer id) throws CustomerIdException {
        customerService.deleteCustomer(id);
        return ResponseEntity.accepted() //
                .build();
    }
}
