package com.tejatechview.Departmentservice.service.impl;

import com.tejatechview.Departmentservice.dto.DepartmentDto;
import com.tejatechview.Departmentservice.entity.Department;
import com.tejatechview.Departmentservice.exception.ResourceNotFoundException;
import com.tejatechview.Departmentservice.repository.DepartmentRepository;
import com.tejatechview.Departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        Department department = new Department(
                departmentDto.getId(),
                departmentDto.getDepartmentName(),
                departmentDto.getDepartmentDescription(),
                departmentDto.getDepartmentCode()
        );
        Department department1 = departmentRepository.save(department);

        DepartmentDto departmentDto1 = new DepartmentDto(
                department1.getId(),
                department1.getDepartmentName(),
                department1.getDepartmentDescription(),
                department1.getDepartmentCode()
        );
        return departmentDto1;

    }

    @Override
    public Object getDepartmentByCode(String code) {
        Department department = departmentRepository.findByDepartmentCode(code);
        if(department ==  null){
            return new ResourceNotFoundException().customException(code);
        }
        DepartmentDto departmentDto = new DepartmentDto(
                department.getId(),
                department.getDepartmentName(),
                department.getDepartmentDescription(),
                department.getDepartmentCode()
        );
        return departmentDto;
    }

    @Override
    public Object getDepartmentById(Long code) {
        Department department = departmentRepository.findById(code).get();
        if(department ==  null){
            return new ResourceNotFoundException().customException(code);
        }
        DepartmentDto departmentDto = new DepartmentDto(
                department.getId(),
                department.getDepartmentName(),
                department.getDepartmentDescription(),
                department.getDepartmentCode()
        );
        return departmentDto;
    }
}
