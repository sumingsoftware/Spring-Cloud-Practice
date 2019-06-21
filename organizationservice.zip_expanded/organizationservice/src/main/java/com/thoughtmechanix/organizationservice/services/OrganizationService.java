package com.thoughtmechanix.organizationservice.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thoughtmechanix.organizationservice.model.Organization;
import com.thoughtmechanix.organizationservice.repository.OrganizationRepository;

@Service
public class OrganizationService {
	@Autowired
	private OrganizationRepository organizationRepo;
	public Optional<Organization> getOrg(String organizationId) {
        return organizationRepo.findById(organizationId);
    }

    public void saveOrg(Organization org){
        org.setId( UUID.randomUUID().toString());

        organizationRepo.save(org);

    }

    public void updateOrg(Organization org){
        organizationRepo.save(org);
    }

    public void deleteOrg(Organization org){
        organizationRepo.delete( org);
    }
}
