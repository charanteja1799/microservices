package com.tejatechview.Employeeservice.controller;

import com.tejatechview.Employeeservice.dto.EmployeeDto;
import com.tejatechview.Employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/employees")
@AllArgsConstructor
public class EmployeeController {

    private EmployeeService employeeService;

    @PostMapping("/saveEmployeeDetails")
    public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto employeeDto1 = employeeService.saveEmployee(employeeDto);
        return new ResponseEntity<>(employeeDto1, HttpStatus.CREATED);
    }
    @GetMapping("/getEmployeeDetails/{email}/{code}")
    public ResponseEntity<Object> getEmployeeDetails(@PathVariable("email") String email,
                                                     @PathVariable("code") String code){
        Object employeeDto = employeeService.getEmployeeDetails(email,code);
        return new ResponseEntity<>(employeeDto,HttpStatus.OK);
    }

}
