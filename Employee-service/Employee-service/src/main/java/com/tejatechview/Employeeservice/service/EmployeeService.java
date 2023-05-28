package com.tejatechview.Employeeservice.service;

import com.tejatechview.Employeeservice.dto.EmployeeDto;

public interface EmployeeService {
    public EmployeeDto saveEmployee(EmployeeDto employeeDto);

    public Object getEmployeeDetails(String email,String code);
}
