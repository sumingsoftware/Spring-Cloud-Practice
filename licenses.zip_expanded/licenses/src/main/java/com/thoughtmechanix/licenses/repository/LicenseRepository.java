package com.thoughtmechanix.licenses.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.thoughtmechanix.licenses.model.License;

@Repository
public interface LicenseRepository extends MongoRepository<License, String>{
	public List<License> findByOrganizationId(String organizationId);
	public License findByOrganizationIdAndLicenseId(String organizationId, String LicenseId);
}
