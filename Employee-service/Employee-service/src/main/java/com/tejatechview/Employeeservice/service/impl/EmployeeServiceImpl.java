package com.tejatechview.Employeeservice.service.impl;

import com.tejatechview.Employeeservice.dto.APIResponseDto;
import com.tejatechview.Employeeservice.dto.DepartmentDto;
import com.tejatechview.Employeeservice.dto.EmployeeDto;
import com.tejatechview.Employeeservice.dto.OrganizationDto;
import com.tejatechview.Employeeservice.entity.Employee;
import com.tejatechview.Employeeservice.exception.ResouceNotFoundException;
import com.tejatechview.Employeeservice.repository.EmployeeRepository;
import com.tejatechview.Employeeservice.service.APIClient;
import com.tejatechview.Employeeservice.service.EmployeeService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;
    private RestTemplate restTemplate;
    // private WebClient webClient;
    private static Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    // private APIClient apiClient;
    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee(
                employeeDto.getId(),
                employeeDto.getFirstname(),
                employeeDto.getLastname(),
                employeeDto.getEmail(),
                employeeDto.getDepartmentCode(),
                employeeDto.getOrganizationCode()
        );
        Employee employee1 = employeeRepository.save(employee);
        EmployeeDto employeeDto1 = new EmployeeDto(
                employee1.getId(),
                employee1.getFirstname(),
                employee1.getLastname(),
                employee1.getEmail(),
                employee1.getDepartmentCode(),
                employee1.getOrganizationCode()
        );
        return employeeDto1;

    }

    @Override
//     @CircuitBreaker(name = "${spring.application.name}",fallbackMethod = "getDefaultDepartment")
    @Retry(name = "${spring.application.name}",fallbackMethod = "getDefaultDepartment")
    public Object getEmployeeDetails(String email,String code) {
        logger.info("Inside Retry method..");
        Employee employee = employeeRepository.findByEmail(email);
        if(employee == null) {
            return new ResouceNotFoundException().customException(email);
        }
        ResponseEntity<DepartmentDto> forEntity = restTemplate.getForEntity("http://localhost:8080/api/departments/getDepartmentDetailsById/" + employee.getId(),
                DepartmentDto.class);
        DepartmentDto departmentDto = forEntity.getBody();
        ResponseEntity<OrganizationDto> organizationDtoResponseEntity = restTemplate.getForEntity("http://localhost:8083/organizationDetails/" + code,
                OrganizationDto.class);
        OrganizationDto organizationDto = organizationDtoResponseEntity.getBody();

//        OrganizationDto organizationDto = webClient.get()
//                .uri("http://localhost:8083/organizationDetails/" + employee.getOrganizationCode())
//                .retrieve()
//                .bodyToMono(OrganizationDto.class)
//                .block();


       // DepartmentDto departmentDto = apiClient.getDepartmentDetailsById(employee.getId());
        EmployeeDto employeeDto = new EmployeeDto(
                employee.getId(),
                employee.getFirstname(),
                employee.getLastname(),
                employee.getEmail(),
                departmentDto.getDepartmentCode(),
                organizationDto.getOrganizationCode()
        );

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployeeDto(employeeDto);
        apiResponseDto.setDepartmentDto(departmentDto);
        apiResponseDto.setOrganizationDto(organizationDto);
        return apiResponseDto;

    }
    public Object getDefaultDepartment(String email, String code, Exception exception) {
        Employee employee = employeeRepository.findByEmail(email);
        if(employee == null) {
            return new ResouceNotFoundException().customException(email);
        }
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setDepartmentCode("RD");
        departmentDto.setDepartmentName("R AND D Research");
        departmentDto.setDepartmentDescription("About Research");
        OrganizationDto organizationDto = new OrganizationDto();
        organizationDto.setOrganizationCode("NO DATA");
        organizationDto.setOrganizationDesc("NO DATA");
        organizationDto.setOrganizationName("NO DATA");
        organizationDto.setCreateDate(null);
        EmployeeDto employeeDto = new EmployeeDto(
                employee.getId(),
                employee.getFirstname(),
                employee.getLastname(),
                employee.getEmail(),
                departmentDto.getDepartmentCode(),
                organizationDto.getOrganizationCode()
        );

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployeeDto(employeeDto);
        apiResponseDto.setDepartmentDto(departmentDto);
        apiResponseDto.setOrganizationDto(organizationDto);
        return apiResponseDto;

    }
    }
