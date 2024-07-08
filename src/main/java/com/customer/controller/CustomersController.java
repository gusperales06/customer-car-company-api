package com.customer.controller;

import com.customer.exception.CustomerIdException;
import com.customer.model.CustomerDTO;
import com.customer.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(path = "/api/v1/customer")
public class CustomersController {
    private final ICustomerService customerService;

    @Autowired
    public CustomersController(ICustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(path = "/all", method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllCustomers() {
        var response = customerService.getAllCustomers();
        return ResponseEntity.ok(response);
    }

    @RequestMapping(path = "/id/{id}", method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCustomerById(@PathVariable(name = "id") final Integer id) throws CustomerIdException {
        var response = customerService.getCustomerId(id);
        return ResponseEntity.ok(response);
    }

    @RequestMapping(method = POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createCustomer(@RequestBody final CustomerDTO newCustomer) {
        var customerCreated = customerService.createCustomer(newCustomer);
        var newURL = ServletUriComponentsBuilder.fromCurrentRequest() //
                .path("/id/{id}")
                .buildAndExpand(customerCreated.getCustomerId())
                .toUri();
        return ResponseEntity.created(newURL) //
                .build();
    }

    @RequestMapping(path = "/id/{id}", method = DELETE)
    public ResponseEntity<?> deleteCustomer(@PathVariable(name = "id") final Integer id) throws CustomerIdException {
        customerService.deleteCustomer(id);
        return ResponseEntity.accepted() //
                .build();
    }
}
