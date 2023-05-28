package com.example.organizationservice.controller;

import com.example.organizationservice.dto.OrganizationDto;
import com.example.organizationservice.service.OrganizationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class OrganizationController {
    private OrganizationService organizationService;

    @PostMapping("/organizationDetailsSave")
    public OrganizationDto saveOrganizationDetails(@RequestBody OrganizationDto organizationDto){
        OrganizationDto organizationDto1 = organizationService.saveOrganization(organizationDto);
        return  organizationDto1;
    }

    @GetMapping("/organizationDetails/{id}")
    public OrganizationDto saveOrganizationDetails(@PathVariable String id){
        OrganizationDto organizationDto = organizationService.organizationDetails(id);
        return organizationDto;
    }

}
