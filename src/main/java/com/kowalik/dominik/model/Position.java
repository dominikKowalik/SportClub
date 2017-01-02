package com.kowalik.dominik.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by dominik on 2016-12-23.
 */
@Entity
@Table(name = "Stanowiska")
@Component("position")
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_stanowiska")
    private Integer positionId;

    @Column(name = "nazwa", nullable = false, unique = true)
    private String positionName;

    @Column(name = "opis")
    private String description;

    @OneToMany(mappedBy = "position",fetch = FetchType.LAZY)
    @Autowired
    private Set<Employee> employeeSet;


    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Employee> getEmployeeSet() {
        return employeeSet;
    }

    public void setEmployeeSet(Set<Employee> employeeSet) {
        this.employeeSet = employeeSet;
    }



    @Override
    public String toString() {
        return "Position{" +
                "positionId=" + positionId +
                ", positionName='" + positionName + '\'' +
                ", description='" + description + '\'' +
                ", employeeSet=" + employeeSet +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (getPositionId() != null ? !getPositionId().equals(position.getPositionId()) : position.getPositionId() != null)
            return false;
        if (getPositionName() != null ? !getPositionName().equals(position.getPositionName()) : position.getPositionName() != null)
            return false;
        return getDescription() != null ? getDescription().equals(position.getDescription()) : position.getDescription() == null;

    }

    @Override
    public int hashCode() {
        int result = getPositionId() != null ? getPositionId().hashCode() : 0;
        result = 31 * result + (getPositionName() != null ? getPositionName().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        return result;
    }
}
