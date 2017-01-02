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
@Table(name = "Harmonogramy_Trenignow")
@Component
public class TrainingSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_treningu")
    private Integer trainingScheduleId;

    @Column(name = "czas_rozpoczecia_treningu", nullable = false)
    private LocalDate trainingStartTime;
    /**
     * length of training in minutes
     */
    @Column(name = "dlugosc_treningu", nullable = false)
    @Size(min = 1, max = 60 * 24)
    private Integer trainingLength;

    /**
     * All club members have inserted at least one training schedule
     */
    @ManyToMany(mappedBy = "trainingScheduleSet")
    private Set<ClubMember> clubMemberSet;

    public Integer getTrainingScheduleId() {
        return trainingScheduleId;
    }

    public void setTrainingScheduleId(Integer trainingScheduleId) {
        this.trainingScheduleId = trainingScheduleId;
    }

    public LocalDate getTrainingStartTime() {
        return trainingStartTime;
    }

    public void setTrainingStartTime(LocalDate trainingStartTime) {
        this.trainingStartTime = trainingStartTime;
    }

    public Integer getTrainingLength() {
        return trainingLength;
    }

    public void setTrainingLength(Integer trainingLength) {
        this.trainingLength = trainingLength;
    }

    public Set<ClubMember> getClubMemberSet() {
        return clubMemberSet;
    }

    public void setClubMemberSet(Set<ClubMember> clubMemberSet) {
        this.clubMemberSet = clubMemberSet;
    }



    @Override
    public String toString() {
        return "TrainingSchedule{" +
                "trainingScheduleId=" + trainingScheduleId +
                ", trainingStartTime=" + trainingStartTime +
                ", trainingLength=" + trainingLength +
                ", clubMemberSet=" + clubMemberSet +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TrainingSchedule that = (TrainingSchedule) o;

        if (getTrainingScheduleId() != null ? !getTrainingScheduleId().equals(that.getTrainingScheduleId()) : that.getTrainingScheduleId() != null)
            return false;
        if (getTrainingStartTime() != null ? !getTrainingStartTime().equals(that.getTrainingStartTime()) : that.getTrainingStartTime() != null)
            return false;
        return getTrainingLength() != null ? getTrainingLength().equals(that.getTrainingLength()) : that.getTrainingLength() == null;

    }

    @Override
    public int hashCode() {
        int result = getTrainingScheduleId() != null ? getTrainingScheduleId().hashCode() : 0;
        result = 31 * result + (getTrainingStartTime() != null ? getTrainingStartTime().hashCode() : 0);
        result = 31 * result + (getTrainingLength() != null ? getTrainingLength().hashCode() : 0);
        return result;
    }
}
