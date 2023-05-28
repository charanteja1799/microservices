package com.example.organizationservice.mapper;

import com.example.organizationservice.dto.OrganizationDto;
import com.example.organizationservice.entity.Organization;

public class OrganizationMapper {
    public static OrganizationDto mapToOrganizationDto(Organization organization){
        OrganizationDto organizationDto = new OrganizationDto(
                organization.getId(),
                organization.getOrganizationName(),
                organization.getOrganizationDesc(),
                organization.getOrganizationCode(),
                organization.getCreateDate()
        );
        return organizationDto;
    }
    public static Organization mapToOrganization(OrganizationDto organizationDto){
        Organization organization = new Organization(
                organizationDto.getId(),
                organizationDto.getOrganizationName(),
                organizationDto.getOrganizationDesc(),
                organizationDto.getOrganizationCode(),
                organizationDto.getCreateDate()
        );
        return organization;
    }
}
