package com.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EntityScan(basePackages = "com.customer")
@EnableJpaRepositories(basePackages = "com.customer")
@EnableTransactionManagement
@SpringBootApplication(scanBasePackages = "com.customer")
public class CustomerCarCompanyApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerCarCompanyApiApplication.class, args);
    }

}
