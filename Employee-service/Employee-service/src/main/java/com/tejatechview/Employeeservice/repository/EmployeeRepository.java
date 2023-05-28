package com.tejatechview.Employeeservice.repository;

import com.tejatechview.Employeeservice.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    Employee findByEmail(String email);
}
