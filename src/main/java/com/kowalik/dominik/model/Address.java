package com.kowalik.dominik.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by dominik on 2016-12-22.
 */

@Entity
@Table(name="Adresy")
@Component("address")
public class Address{
    @Id
    @Column(name = "id_adresu")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer addressId;

    @Basic(optional = false)
    @Column(name = "numer")
    private Integer number;

    @Basic(optional = false)
    @Column(name = "ulica", length = 30)
    private String street;

    @Basic(optional = false)
    @Column(name = "kod_pocztowy", length = 30)
    private String zipCode;

    @Basic(optional = false)
    @Column(name = "miasto", length = 30)
    private String city;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_wojewodztwa")
    @Basic(optional = false)
    @Autowired
    private Voivodeship voivodeship;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "address")
    @Basic(optional = false)
    private Set<Employee> employeeSet;

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Voivodeship getVoivodeship() {
        return voivodeship;
    }

    public void setVoivodeship(Voivodeship voivodeship) {
        this.voivodeship = voivodeship;
    }

    public Set<Employee> getEmployeeSet() {
        return employeeSet;
    }

    public void setEmployeeSet(Set<Employee> employeeSet) {
        this.employeeSet = employeeSet;
    }


    @Override
    public String toString() {
        return "Address{" +
                "addressId=" + addressId +
                ", number=" + number +
                ", street='" + street + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", city='" + city + '\'' +
                ", voivodeship=" + voivodeship +
                ", employeeSet=" + employeeSet +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (getAddressId() != null ? !getAddressId().equals(address.getAddressId()) : address.getAddressId() != null)
            return false;
        if (getNumber() != null ? !getNumber().equals(address.getNumber()) : address.getNumber() != null) return false;
        if (getStreet() != null ? !getStreet().equals(address.getStreet()) : address.getStreet() != null) return false;
        if (getZipCode() != null ? !getZipCode().equals(address.getZipCode()) : address.getZipCode() != null)
            return false;
        return getCity() != null ? getCity().equals(address.getCity()) : address.getCity() == null;

    }

    @Override
    public int hashCode() {
        int result = getAddressId() != null ? getAddressId().hashCode() : 0;
        result = 31 * result + (getNumber() != null ? getNumber().hashCode() : 0);
        result = 31 * result + (getStreet() != null ? getStreet().hashCode() : 0);
        result = 31 * result + (getZipCode() != null ? getZipCode().hashCode() : 0);
        result = 31 * result + (getCity() != null ? getCity().hashCode() : 0);
        return result;
    }
}
