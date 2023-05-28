package com.tejatechview.Employeeservice.service;

import com.tejatechview.Employeeservice.dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// @FeignClient(url = "http://localhost:8080",value = "DEPARTMENT-SERVICE")
//@FeignClient(name = "DEPARTMENT-SERVICE")
public interface APIClient {

    @GetMapping("/api/departments/getDepartmentDetailsById/{id}")
    DepartmentDto getDepartmentDetailsById(@PathVariable("id") Long code);


}
