package com.kowalik.dominik.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by dominik on 2016-12-24.
 */

@Entity
@Table(name = "Dyscypliny")
@Component("discipline")
public class Discipline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dyscypliny")
    private Integer disciplineId;

    @Column(name = "nazwa", length = 30, unique = true, nullable = false)
    private String name;

    @Column(name = "opis", length = 300)
    private String description;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "disciplineSet")
    private Set<ClubMember> clubMemberSet;

    public Integer getDisciplineId() {
        return disciplineId;
    }

    public void setDisciplineId(Integer disciplineId) {
        this.disciplineId = disciplineId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<ClubMember> getClubMemberSet() {
        return clubMemberSet;
    }

    public void setClubMemberSet(Set<ClubMember> clubMemberSet) {
        this.clubMemberSet = clubMemberSet;
    }



    @Override
    public String toString() {
        return "Discipline{" +
                "disciplineId=" + disciplineId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", clubMemberSet=" + clubMemberSet +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Discipline that = (Discipline) o;

        if (getDisciplineId() != null ? !getDisciplineId().equals(that.getDisciplineId()) : that.getDisciplineId() != null)
            return false;
        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
        return getDescription() != null ? getDescription().equals(that.getDescription()) : that.getDescription() == null;

    }

    @Override
    public int hashCode() {
        int result = getDisciplineId() != null ? getDisciplineId().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        return result;
    }
}
