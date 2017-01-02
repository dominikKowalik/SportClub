package com.kowalik.dominik.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;

/**
 * Created by dominik on 2016-12-24.
 */

@Entity
@Table(name = "Harmonogramy_pracy")
@Component("workSchedule")
public class WorkSchedule {
    @Id
    @Column(name = "id_harmonogramu")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer workScheduleId;

    @Column(name = "czas_rozpoczecia_pracy", nullable = false)
    private LocalDate workStartTime;
    /**
     * length of work in minutes
     */
    @Column(name = "dlugosc_czasu_pracy", nullable = false)
    @Size(min = 1, max = 60 * 24)
    private Integer workLength;

    @ManyToMany(mappedBy = "workScheduleSet")
    private Set<Employee> employeeSet;

    public Integer getWorkScheduleId() {
        return workScheduleId;
    }

    public void setWorkScheduleId(Integer workScheduleId) {
        this.workScheduleId = workScheduleId;
    }

    public LocalDate getWorkStartTime() {
        return workStartTime;
    }

    public void setWorkStartTime(LocalDate workStartTime) {
        this.workStartTime = workStartTime;
    }

    public Integer getWorkLength() {
        return workLength;
    }

    public void setWorkLength(Integer workLength) {
        this.workLength = workLength;
    }

    public Set<Employee> getEmployeeSet() {
        return employeeSet;
    }

    public void setEmployeeSet(Set<Employee> employeeSet) {
        this.employeeSet = employeeSet;
    }



    @Override
    public String toString() {
        return "WorkSchedule{" +
                "workScheduleId=" + workScheduleId +
                ", workStartTime=" + workStartTime +
                ", workLength=" + workLength +
                ", employeeSet=" + employeeSet +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WorkSchedule that = (WorkSchedule) o;

        if (getWorkScheduleId() != null ? !getWorkScheduleId().equals(that.getWorkScheduleId()) : that.getWorkScheduleId() != null)
            return false;
        if (getWorkStartTime() != null ? !getWorkStartTime().equals(that.getWorkStartTime()) : that.getWorkStartTime() != null)
            return false;
        return getWorkLength() != null ? getWorkLength().equals(that.getWorkLength()) : that.getWorkLength() == null;

    }

    @Override
    public int hashCode() {
        int result = getWorkScheduleId() != null ? getWorkScheduleId().hashCode() : 0;
        result = 31 * result + (getWorkStartTime() != null ? getWorkStartTime().hashCode() : 0);
        result = 31 * result + (getWorkLength() != null ? getWorkLength().hashCode() : 0);
        return result;
    }
}
