package com.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.customer")
public class CustomerCarCompanyApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerCarCompanyApiApplication.class, args);
    }

}
