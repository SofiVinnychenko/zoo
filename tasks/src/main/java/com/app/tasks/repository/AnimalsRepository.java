package com.app.tasks.repository;

import com.app.tasks.entity.Animals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnimalsRepository extends JpaRepository<Animals, Long> {

    @Query("""
        SELECT a FROM Animals a 
        JOIN FETCH a.enclosure e 
        JOIN FETCH e.employee emp
        WHERE a.id = :id
    """)
    Optional<Animals> findByIdWithEmployees(@Param("id") Long id);
}
