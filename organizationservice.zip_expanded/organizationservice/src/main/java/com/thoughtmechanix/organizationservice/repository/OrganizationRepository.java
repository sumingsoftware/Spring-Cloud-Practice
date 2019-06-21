package com.thoughtmechanix.organizationservice.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.thoughtmechanix.organizationservice.model.Organization;
@Repository
public interface OrganizationRepository extends MongoRepository<Organization, String>{
	public Optional<Organization> findById(String organizationId);
}
