package com.app.tasks.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@Table(name = "feedingschedule")
public class FeedingSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Time timefeed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enclos_id")
    private Enclosure enclose;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "feedingschedule_employees",
            joinColumns = @JoinColumn(name = "feedingschedule_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id")
    )
    private Set<Employees> employees = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "feedingschedule_food",
            joinColumns = @JoinColumn(name = "feedingschedule_id"),
            inverseJoinColumns = @JoinColumn(name = "food_id")
    )
    private Set<Food> foods = new HashSet<>();

    public FeedingSchedule(Time timefeed) {
        this.timefeed = timefeed;
    }

    public FeedingSchedule() {
    }
}
