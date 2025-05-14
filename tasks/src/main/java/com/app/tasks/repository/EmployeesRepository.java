package com.app.tasks.repository;

import com.app.tasks.entity.Employees;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeesRepository extends JpaRepository<Employees, Long> {

    List<Employees> findAllByRole(String role);
}
