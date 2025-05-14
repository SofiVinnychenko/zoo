package com.app.tasks.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@Table(name = "food")
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Date expirationDate;

    @Column(columnDefinition = "TEXT")
    private String storageConditions;

    @ManyToMany(mappedBy = "foods", fetch = FetchType.LAZY)
    private Set<FeedingSchedule> feedingSchedules = new HashSet<>();

    public Food(String title, Date expirationDate, String storageConditions) {
        this.title = title;
        this.expirationDate = expirationDate;
        this.storageConditions = storageConditions;
    }

    public Food() {
    }

}
