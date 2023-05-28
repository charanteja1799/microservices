package com.tejatechview.Departmentservice.service;

import com.tejatechview.Departmentservice.dto.DepartmentDto;

public interface DepartmentService {
    DepartmentDto saveDepartment(DepartmentDto departmentDto);
    Object getDepartmentByCode(String code);

    Object getDepartmentById (Long code);
}
