package com.tejatechview.Departmentservice.controller;

import com.tejatechview.Departmentservice.dto.DepartmentDto;
import com.tejatechview.Departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/departments")
@AllArgsConstructor
@RefreshScope
public class DepartmentController {

    private DepartmentService departmentService;

    @PostMapping("/saveDepartmentDetails")
    public ResponseEntity<DepartmentDto> saveDepartment(@RequestBody DepartmentDto departmentDto){
        return new ResponseEntity<>(departmentService.saveDepartment(departmentDto),HttpStatus.CREATED);
    }
    @GetMapping("/getDepartmentDetails/{code}")
    public ResponseEntity<Object> getDepartmentDetails(@PathVariable("code") String code){
        return new ResponseEntity<>(departmentService.getDepartmentByCode(code),HttpStatus.OK);
    }

    @GetMapping("/getDepartmentDetailsById/{id}")
    public ResponseEntity<Object> getDepartmentDetailsById(@PathVariable("id") Long code){
        return new ResponseEntity<>(departmentService.getDepartmentById(code),HttpStatus.OK);
    }

}
