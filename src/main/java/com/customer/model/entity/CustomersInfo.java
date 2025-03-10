package com.customer.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class CustomersInfo {
    @Column
    private String gender;

    @Column(name = "household_income")
    private String householdIncome;

    @Column
    private String email;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomersInfo)) return false;
        CustomersInfo that = (CustomersInfo) o;
        return Objects.equals(gender, that.gender) && Objects.equals(householdIncome, that.householdIncome) &&
                Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gender, householdIncome, email);
    }
}
