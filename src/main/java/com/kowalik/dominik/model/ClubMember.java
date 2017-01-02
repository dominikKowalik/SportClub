package com.kowalik.dominik.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * Created by dominik on 2016-12-24.
 */

@Entity
@Table(name = "Zawodnicy")
@Component("clubMember")
public class ClubMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_zawodnika")
    private Integer clubMemberId;

    @Column(name = "imie", length = 30, nullable = false)
    private String firstName;

    @Column(name = "nazwisko", length = 30, nullable = false)
    private String lastName;

    @Column(name = "wiek", length = 30, nullable = false)
    @Size(min = 10, max = 99)
    private Integer age;

    @ManyToOne(cascade = {CascadeType.ALL, CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_klubu")
    @Autowired
    private Club club;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "id_konta", nullable = true)
    private Account account;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "Zawodnicy_Dyscypliny",
            joinColumns = {@JoinColumn(name = "id_zawodnika")},
            inverseJoinColumns = {@JoinColumn(name = "id_pracownika")})

    private Set<Discipline> disciplineSet;


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "Zawodnicy_Harmonogramy",
            joinColumns = {@JoinColumn(name = "id_zawodnika")},
            inverseJoinColumns = {@JoinColumn(name = "id_harmonogramu")})
    private Set<TrainingSchedule> trainingScheduleSet;

    @OneToMany( mappedBy = "clubMember", fetch = FetchType.EAGER)
    private Set<Achievement> achievementSet;

    public Integer getClubMemberId() {
        return clubMemberId;
    }

    public void setClubMemberId(Integer clubMemberId) {
        this.clubMemberId = clubMemberId;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Set<Discipline> getDisciplineSet() {
        return disciplineSet;
    }

    public void setDisciplineSet(Set<Discipline> disciplineSet) {
        this.disciplineSet = disciplineSet;
    }

    public Set<TrainingSchedule> getTrainingScheduleSet() {
        return trainingScheduleSet;
    }

    public void setTrainingScheduleSet(Set<TrainingSchedule> trainingScheduleSet) {
        this.trainingScheduleSet = trainingScheduleSet;
    }

    public Set<Achievement> getAchievementSet() {
        return achievementSet;
    }

    public void setAchievementSet(Set<Achievement> achievementSet) {
        this.achievementSet = achievementSet;
    }


    @Override
    public String toString() {
        return "ClubMember{" +
                "clubMemberId=" + clubMemberId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", club=" + club +
                ", account=" + account +
                ", disciplineSet=" + disciplineSet +
                ", trainingScheduleSet=" + trainingScheduleSet +
                ", achievementSet=" + achievementSet +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClubMember that = (ClubMember) o;

        if (getClubMemberId() != null ? !getClubMemberId().equals(that.getClubMemberId()) : that.getClubMemberId() != null)
            return false;
        if (getFirstName() != null ? !getFirstName().equals(that.getFirstName()) : that.getFirstName() != null)
            return false;
        if (getLastName() != null ? !getLastName().equals(that.getLastName()) : that.getLastName() != null)
            return false;
        if (getAge() != null ? !getAge().equals(that.getAge()) : that.getAge() != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = getClubMemberId() != null ? getClubMemberId().hashCode() : 0;
        result = 31 * result + (getFirstName() != null ? getFirstName().hashCode() : 0);
        result = 31 * result + (getLastName() != null ? getLastName().hashCode() : 0);
        result = 31 * result + (getAge() != null ? getAge().hashCode() : 0);
        return result;
    }
}

