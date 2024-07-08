package com.customer.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "Customers")
@NoArgsConstructor
@Getter
public class Customers {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "customer_id")
    private Integer customerId;

    @Setter
    @Column(name = "first_name")
    private String firstName;

    @Setter
    @Column(name = "last_name")
    private String lastName;

    @Setter
    @Column
    private String birthdate;

    @Setter
    @Column
    private String gender;

    @Setter
    @Column(name = "household_income")
    private String householdIncome;

    @Setter
    @Column(name = "phone_number")
    private String phoneNumber;

    @Setter
    @Column
    private String email;

    public Customers(String firstName, String lastName, String birthdate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customers customers = (Customers) o;
        return Objects.equals(customerId, customers.customerId) && Objects.equals(firstName, customers.firstName) && Objects.equals(lastName, customers.lastName) && Objects.equals(birthdate, customers.birthdate) && Objects.equals(gender, customers.gender) && Objects.equals(householdIncome, customers.householdIncome) && Objects.equals(phoneNumber, customers.phoneNumber) && Objects.equals(email, customers.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, firstName, lastName, birthdate, gender, householdIncome, phoneNumber, email);
    }
}
