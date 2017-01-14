package com.kowalik.dominik.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by dominik on 2016-12-22.
 */

@Entity
@Table(name = "Pracownicy")
@Component("employee")
public class Employee{

    @Id
    @Column(name = "id_zawodnika")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer employeeId;

    @Basic(optional = false)
    @Column(name = "imie", length = 30)
    private String firstname;

    @Basic(optional = false)
    @Column(name = "nazwisko", length = 30)
    private String lastname;

    @Basic(optional = false)
    @Column(name = "wyksztalcenie", length = 30)
    private String education;

    @Basic(optional = false)
    @Column(name = "imie_matki", length = 30)
    private String motherName;

    @Basic(optional = false)
    @Column(name = "imie_ojca", length = 30)
    private String fatherName;

    @Basic(optional = false)
    @Column(name = "pesel", length = 30, unique = true)
    private String pesel;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_klubu")
    @Basic(optional = false)
    @JsonBackReference
    private Club club;

    @OneToOne(fetch = FetchType.EAGER ,cascade = CascadeType.ALL)
    @JoinColumn(name = "id_adresu")
    private Address address;


    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_stanowiska")
    private Position position;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_konta")
    private Account account;


    @ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinTable(name="Pracownicy_Harmonogramy",
            joinColumns={@JoinColumn(name="id_pracownika")},
            inverseJoinColumns={@JoinColumn(name="id_hramonogramu")})
    private Set<WorkSchedule> workScheduleSet;

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastName) {
        this.lastname = lastName;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Set<WorkSchedule> getWorkScheduleSet() {
        return workScheduleSet;
    }

    public void setWorkScheduleSet(Set<WorkSchedule> workScheduleSet) {
        this.workScheduleSet = workScheduleSet;
    }



    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", firstname='" + firstname + '\'' +
                ", lastName='" + lastname + '\'' +
                ", education='" + education + '\'' +
                ", motherName='" + motherName + '\'' +
                ", fatherName='" + fatherName + '\'' +
                ", pesel='" + pesel + '\'' +
                ", club=" + club +
                ", address=" + address +
                ", position=" + position +
                ", account=" + account +
                ", workScheduleSet=" + workScheduleSet +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        if (getEmployeeId() != null ? !getEmployeeId().equals(employee.getEmployeeId()) : employee.getEmployeeId() != null)
            return false;
        if (getFirstname() != null ? !getFirstname().equals(employee.getFirstname()) : employee.getFirstname() != null)
            return false;
        if (getLastname() != null ? !getLastname().equals(employee.getLastname()) : employee.getLastname() != null)
            return false;
        if (getEducation() != null ? !getEducation().equals(employee.getEducation()) : employee.getEducation() != null)
            return false;
        if (getMotherName() != null ? !getMotherName().equals(employee.getMotherName()) : employee.getMotherName() != null)
            return false;
        if (getFatherName() != null ? !getFatherName().equals(employee.getFatherName()) : employee.getFatherName() != null)
            return false;
        return getPesel() != null ? getPesel().equals(employee.getPesel()) : employee.getPesel() == null;

    }

    @Override
    public int hashCode() {
        int result = getEmployeeId() != null ? getEmployeeId().hashCode() : 0;
        result = 31 * result + (getFirstname() != null ? getFirstname().hashCode() : 0);
        result = 31 * result + (getLastname() != null ? getLastname().hashCode() : 0);
        result = 31 * result + (getEducation() != null ? getEducation().hashCode() : 0);
        result = 31 * result + (getMotherName() != null ? getMotherName().hashCode() : 0);
        result = 31 * result + (getFatherName() != null ? getFatherName().hashCode() : 0);
        result = 31 * result + (getPesel() != null ? getPesel().hashCode() : 0);
        return result;
    }
}

/*
jak ustaić w account ze jak istnieje to musi byc not null
 */
