package com.kowalik.dominik.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by dominik on 2016-12-24.
 */
@Entity
@Table(name  = "Osiagniecia")
@Component("achievement")
public class Achievement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_osiagniecia")
    private Integer achievementId;

    @Column(name = "data", nullable = false)
    private LocalDate date;

    @Column(name = "miejsce", nullable = false)
    private Integer position;

    @Column(name = "nazwa_zawodow", nullable = false)
    private String eventName;

    @Column(name = "miejscowosc", nullable = false)
    private String locality;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_zawodnika", nullable = false)
    @Autowired
    @Qualifier("clubMember")
    private ClubMember clubMember;

    public Integer getAchievementId() {
        return achievementId;
    }

    public void setAchievementId(Integer achievementId) {
        this.achievementId = achievementId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public ClubMember getClubMember() {
        return clubMember;
    }

    public void setClubMember(ClubMember clubMember) {
        this.clubMember = clubMember;
    }

    @Override
    public String toString() {
        return "Achievement{" +
                "achievementId=" + achievementId +
                ", date=" + date +
                ", position=" + position +
                ", eventName='" + eventName + '\'' +
                ", locality='" + locality + '\'' +
                ", clubMember=" + clubMember +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Achievement that = (Achievement) o;

        if (getAchievementId() != null ? !getAchievementId().equals(that.getAchievementId()) : that.getAchievementId() != null)
            return false;
        if (getDate() != null ? !getDate().equals(that.getDate()) : that.getDate() != null) return false;
        if (getPosition() != null ? !getPosition().equals(that.getPosition()) : that.getPosition() != null)
            return false;
        if (getEventName() != null ? !getEventName().equals(that.getEventName()) : that.getEventName() != null)
            return false;
        return getLocality() != null ? getLocality().equals(that.getLocality()) : that.getLocality() == null;

    }

    @Override
    public int hashCode() {
        int result = getAchievementId() != null ? getAchievementId().hashCode() : 0;
        result = 31 * result + (getDate() != null ? getDate().hashCode() : 0);
        result = 31 * result + (getPosition() != null ? getPosition().hashCode() : 0);
        result = 31 * result + (getEventName() != null ? getEventName().hashCode() : 0);
        result = 31 * result + (getLocality() != null ? getLocality().hashCode() : 0);
        return result;
    }
}
