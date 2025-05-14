package com.app.tasks.repository;

import com.app.tasks.entity.Enclosure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface EnclosureRepository extends JpaRepository<Enclosure, Long> {
}
