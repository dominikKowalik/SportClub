package com.kowalik.dominik.configuration;

import com.kowalik.dominik.model.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by dominik on 2016-12-22.
 */

@Configuration
@ComponentScan("com.kowalik.dominik")
public class BeansModel {

    @Bean
    @Scope("prototype")
    public Set<Account> accountSet(){
        return new HashSet<>();
    }

    @Bean
    @Scope("prototype")
    public Set<Achievement> achievementSet(){
        return new HashSet<>();
    }

    @Bean
    @Scope("prototype")
    public Set<Address> addressSet(){
        return new HashSet<>();
    }

    @Bean
    @Scope("prototype")
    public Set<Building> buildingSet(){
        return new HashSet<>();
    }

    @Bean
    @Scope("prototype")
    public Set<Club> clubSet(){
        return new HashSet<>();
    }

    @Bean
    @Scope("prototype")
    public Set<ClubMember> clubMemberSet(){
        return new HashSet<>();
    }

    @Bean
    @Scope("prototype")
    public Set<Discipline> disciplineSet(){
        return new HashSet<>();
    }

    @Bean
    @Scope("prototype")
    public Set<Employee> employeeSet(){
        return new HashSet<>();
    }


    @Bean
    @Scope("prototype")
    public Set<Position> positionSet(){
        return new HashSet<>();
    }


    @Bean
    @Scope("prototype")
    public Set<TrainingSchedule> trainingScheduleSet(){
        return new HashSet<>();
    }

    @Bean
    @Scope("prototype")
    public Set<Voivodeship> voivodeshipSet(){
        return new HashSet<>();
    }

    @Bean
    @Scope("prototype")
    public Set<WorkSchedule> workScheduleSet(){
        return new HashSet<>();
    }

    @Bean
    @Scope("prototype")
    public LocalDate localDate(){ return LocalDate.now();}
}
