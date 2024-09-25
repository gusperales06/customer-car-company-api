package com.customer.model.entity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "Customers")
public class Customers {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "customer_id")
    private Integer customerId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column
    private LocalDate birthdate;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Embedded
    private CustomersInfo customersInfo;

    public Customers() {
    }

    public Customers(String firstName, String lastName, LocalDate birthdate, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.phoneNumber = phoneNumber;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
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

    public CustomersInfo getCustomersInfo() {
        return customersInfo;
    }

    public void setCustomersInfo() {
        this.customersInfo = new CustomersInfo();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customers)) return false;
        Customers customers = (Customers) o;
        return Objects.equals(customerId, customers.customerId) && Objects.equals(firstName, customers.firstName) &&
                Objects.equals(lastName, customers.lastName) && Objects.equals(birthdate, customers.birthdate) &&
                Objects.equals(phoneNumber, customers.phoneNumber) && Objects.equals(customersInfo, customers.customersInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, firstName, lastName, birthdate, phoneNumber, customersInfo);
    }
}
