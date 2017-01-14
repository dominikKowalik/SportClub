package com.kowalik.dominik.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * Created by dominik on 2016-12-23.
 */


@Entity
@Table(name = "Konta")
@Component("account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id_konta")
    private Integer accountId;

    @Basic(optional = false)
    @Column(name = "nazwa_uzytkownika", length = 30, unique = true)
    private String login;

    @Basic(optional = false)
    @Column(name = "haslo", length = 30, unique = true)
    private String password;

    @Column(name = "rola", length = 30, nullable = false)
    private String role;

    @OneToOne(mappedBy = "account",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    private Employee employee;

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (getAccountId() != null ? !getAccountId().equals(account.getAccountId()) : account.getAccountId() != null)
            return false;
        if (getLogin() != null ? !getLogin().equals(account.getLogin()) : account.getLogin() != null) return false;
        if (getPassword() != null ? !getPassword().equals(account.getPassword()) : account.getPassword() != null)
            return false;
        return getRole() != null ? getRole().equals(account.getRole()) : account.getRole() == null;

    }

    @Override
    public int hashCode() {
        int result = getAccountId() != null ? getAccountId().hashCode() : 0;
        result = 31 * result + (getLogin() != null ? getLogin().hashCode() : 0);
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        result = 31 * result + (getRole() != null ? getRole().hashCode() : 0);
        return result;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}


