package com.kowalik.dominik.model;

/**
 * Created by dominik on 2016-12-22.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;


/**
 * Class that represents main entity
 */


@Entity
@Table(name="Klub")
@Component("club")
@Scope("prototype")
public class Club {

    @Id
    @Column(name = "id_klubu")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer clubId;

     @Column(name = "rok_zalozenia")
    @Autowired
    private LocalDate dateOfEstablishment;

    @Basic(optional = false)
    @Column(name = "nazwa", length = 30, unique = true)
    private String name;


    @OneToMany(mappedBy = "club", fetch = FetchType.LAZY)
    private Set<Building> buildingSet;

    @OneToMany(mappedBy = "club", fetch = FetchType.LAZY)
    private Set<Employee> employeeSet;

    @OneToMany(mappedBy = "club", cascade = {CascadeType.ALL, CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @Qualifier("clubMemberSet")
    private Set<ClubMember> clubMemberSet;


    public Integer getClubId() {
        return clubId;
    }

    public void setClubId(Integer clubId) {
        this.clubId = clubId;
    }

    public LocalDate getDateOfEstablishment() {
        return dateOfEstablishment;
    }

    public void setDateOfEstablishment(LocalDate dateOfEstablishment) {
        this.dateOfEstablishment = dateOfEstablishment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Building> getBuildingSet() {
        return buildingSet;
    }

    public void setBuildingSet(Set<Building> buildingSet) {
        this.buildingSet = buildingSet;
    }

    public Set<Employee> getEmployeeSet() {
        return employeeSet;
    }

    public void setEmployeeSet(Set<Employee> employeeSet) {
        this.employeeSet = employeeSet;
    }

    public Set<ClubMember> getClubMemberSet() {
        return clubMemberSet;
    }

    public void setClubMemberSet(Set<ClubMember> clubMemberSet) {
        this.clubMemberSet = clubMemberSet;
    }


    @Override
    public String toString() {
        return "Club{" +
                "clubId=" + clubId +
                ", dateOfEstablishment=" + dateOfEstablishment +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Club club = (Club) o;

        if (getClubId() != null ? !getClubId().equals(club.getClubId()) : club.getClubId() != null) return false;
        if (getDateOfEstablishment() != null ? !getDateOfEstablishment().equals(club.getDateOfEstablishment()) : club.getDateOfEstablishment() != null)
            return false;
        return getName() != null ? getName().equals(club.getName()) : club.getName() == null;

    }

    @Override
    public int hashCode() {
        int result = getClubId() != null ? getClubId().hashCode() : 0;
        result = 31 * result + (getDateOfEstablishment() != null ? getDateOfEstablishment().hashCode() : 0);
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        return result;
    }
}
