package com.customer.model.dto;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.util.Objects;

public class CustomerInfoDTO {
    @Size(min = 4, max = 6)
    private String gender;

    @PositiveOrZero(message = "The 'Household Income' must be equal or grater than 0")
    private String householdIncome;

    @Size(min = 10, max = 13, message = "The 'Phone Number' must be valid")
    private String phoneNumber;

    @Pattern(regexp = "^([\\w-\\.]+){1,64}@(\\w&&[^_]+){2,255}.[a-z]{2,}$", message = "The 'E-mail' must be a valid one")
    private String email;

    public CustomerInfoDTO() {
    }

    public CustomerInfoDTO(String gender, String householdIncome, String phoneNumber, String email) {
        this.gender = gender;
        this.householdIncome = householdIncome;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHouseholdIncome() {
        return householdIncome;
    }

    public void setHouseholdIncome(String householdIncome) {
        this.householdIncome = householdIncome;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static class CustomerInfoBuilder {
        private final CustomerDTO parent;
        private String gender;
        private String householdIncome;
        private String phoneNumber;
        private String email;

        public CustomerInfoBuilder(final CustomerDTO parent) {
            this.parent = parent;
        }

        public CustomerInfoBuilder gender(final String gender) {
            this.gender = gender;
            return this;
        }

        public CustomerInfoBuilder householdIncome(final String householdIncome) {
            this.householdIncome = householdIncome;
            return this;
        }

        public CustomerInfoBuilder phoneNumber(final String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public CustomerInfoBuilder email(final String email) {
            this.email = email;
            return this;
        }

        public CustomerDTO build() {
            return parent.customersInfo(new CustomerInfoDTO(gender, householdIncome, phoneNumber, email));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerInfoDTO that = (CustomerInfoDTO) o;
        return Objects.equals(gender, that.gender) && Objects.equals(householdIncome, that.householdIncome) &&
                Objects.equals(phoneNumber, that.phoneNumber) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gender, householdIncome, phoneNumber, email);
    }

    @Override
    public String toString() {
        return "CustomerInfoDTO{" +
                "gender='" + gender + '\'' +
                ", householdIncome='" + householdIncome + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
