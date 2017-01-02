package com.kowalik.dominik.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * Created by dominik on 2016-12-22.
 */

@Entity
@Table(name="Obiekty")
@Component("building")
public class Building {

    @Id
    @Column(name = "id_obiektu")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer buildingId;

    @Basic(optional = false)
    @Column(name = "nazwa", length = 30)
    private String name;

    @Column(name = "email", length = 30)
    private String email;

    @Column(name = "nr_telefonu", length = 30)
    private String phoneNumber;

    @ManyToOne( )
    @JoinColumn(name = "id_klubu")
    @Basic(optional = false)
    @Autowired
    private Club club;


    public Integer getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Integer buildingId) {
        this.buildingId = buildingId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }


    @Override
    public String toString() {
        return "Building{" +
                "buildingId=" + buildingId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", club=" + club +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Building building = (Building) o;

        if (getBuildingId() != null ? !getBuildingId().equals(building.getBuildingId()) : building.getBuildingId() != null)
            return false;
        if (getName() != null ? !getName().equals(building.getName()) : building.getName() != null) return false;
        if (getEmail() != null ? !getEmail().equals(building.getEmail()) : building.getEmail() != null) return false;
        return getPhoneNumber() != null ? getPhoneNumber().equals(building.getPhoneNumber()) : building.getPhoneNumber() == null;

    }

    @Override
    public int hashCode() {
        int result = getBuildingId() != null ? getBuildingId().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        result = 31 * result + (getPhoneNumber() != null ? getPhoneNumber().hashCode() : 0);
        return result;
    }
}
