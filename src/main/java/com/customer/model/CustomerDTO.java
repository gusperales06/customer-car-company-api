package com.customer.model;

import java.util.Objects;
import com.customer.model.CustomerInfoDTO.CustomerInfoBuilder;

public class CustomerDTO {
    private String firstName;
    private String lastName;
    private String birthdate;
    private CustomerInfoDTO customersInfo;

    public CustomerDTO() {
    }

    public CustomerDTO(String firstName, String lastName, String birthdate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public CustomerInfoDTO getCustomersInfo() {
        return customersInfo;
    }

    public CustomerInfoBuilder customerInfo() {
        return new CustomerInfoBuilder(this);
    }

    CustomerDTO customersInfo(CustomerInfoDTO customerInfo) {
        this.customersInfo = customerInfo;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerDTO that = (CustomerDTO) o;
        return Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(birthdate, that.birthdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, birthdate);
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthdate='" + birthdate + '\'' +
                '}';
    }
}
