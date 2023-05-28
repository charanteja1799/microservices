package com.tejatechview.Departmentservice.repository;

import com.tejatechview.Departmentservice.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department,Long> {
    Department findByDepartmentCode(String departmentCode);
}
