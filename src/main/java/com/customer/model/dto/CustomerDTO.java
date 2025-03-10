package com.customer.model.dto;

import com.customer.model.dto.CustomerInfoDTO.CustomerInfoBuilder;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.Objects;

public class CustomerDTO {
    @NotBlank(message = "The 'First Name' should not be null or empty")
    @Size(max = 15, message = "The 'Fist Name' should not be grater than 15")
    private String firstName;

    @NotBlank(message = "The 'Last Name' should not be null or empty")
    @Size(max = 15, message = "The 'Last Name' length should not be grater than 15")
    private String lastName;

    @NotNull(message = "The 'Birthdate' should not be null")
    @Past(message = "The 'Birthdate' should be valid past date")
    private LocalDate birthdate;

    @Size(min = 10, max = 13, message = "The 'Phone Number' must be valid")
    private String phoneNumber;

    private CustomerInfoDTO customersInfo;

    public CustomerDTO() {
    }

    public CustomerDTO(String firstName, String lastName, LocalDate birthdate, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.phoneNumber = phoneNumber;
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

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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
        return Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) &&
                Objects.equals(birthdate, that.birthdate) && Objects.equals(phoneNumber, that.phoneNumber) &&
                Objects.equals(customersInfo, that.customersInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, birthdate, customersInfo);
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthdate=" + birthdate +
                ", customersInfo=" + customersInfo +
                '}';
    }
}
