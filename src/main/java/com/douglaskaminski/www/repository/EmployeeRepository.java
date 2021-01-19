package com.douglaskaminski.www.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.douglaskaminski.www.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
    public Employee findByEmail(String email);
}
