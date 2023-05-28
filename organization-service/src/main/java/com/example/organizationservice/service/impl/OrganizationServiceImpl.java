package com.example.organizationservice.service.impl;

import com.example.organizationservice.dto.OrganizationDto;
import com.example.organizationservice.entity.Organization;
import com.example.organizationservice.mapper.OrganizationMapper;
import com.example.organizationservice.repository.OrganizationRepository;
import com.example.organizationservice.service.OrganizationService;
import com.fasterxml.jackson.databind.util.BeanUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class OrganizationServiceImpl implements OrganizationService {

    private OrganizationRepository organizationRepository;

    @Override
    public OrganizationDto saveOrganization(OrganizationDto organizationDto) {

        Organization organization = OrganizationMapper.mapToOrganization(organizationDto);
        Organization organization1 = organizationRepository.save(organization);
        BeanUtils.copyProperties(organization1,organizationDto);
        return organizationDto;
    }

    @Override
    public OrganizationDto organizationDetails(String id) {
        Organization organization1 = organizationRepository.findByorganizationCode(id);
        OrganizationDto organizationDto = OrganizationMapper.mapToOrganizationDto(organization1);
        return organizationDto;
    }
}
