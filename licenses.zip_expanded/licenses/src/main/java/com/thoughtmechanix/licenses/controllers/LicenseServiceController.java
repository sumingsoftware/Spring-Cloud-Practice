package com.thoughtmechanix.licenses.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.thoughtmechanix.licenses.clients.OrganizationFeignClient;
import com.thoughtmechanix.licenses.model.License;
import com.thoughtmechanix.licenses.model.Organization;
import com.thoughtmechanix.licenses.services.LicenseService;
import com.thoughtmechanix.licenses.utils.UserContextHolder;

@RestController
@RequestMapping(value = "v1/organizations/{organizationId}/licenses")
public class LicenseServiceController {
	@Autowired
	private LicenseService licenseService;
	
	@Autowired
	private OrganizationFeignClient orgFeignCli;
	
	
	@RequestMapping(value="/onlyOrg", method=RequestMethod.GET)
	Optional<Organization> getOrgOnly(@PathVariable("organizationId") String orgId){
		System.out.println(UserContextHolder.getContext().getCorrelationId());
		return orgFeignCli.getOrganization(orgId);
	}
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	List<License> getLicensesByOrg(@PathVariable("organizationId") String orgId){
		return licenseService.getLicenseByOrg(orgId);
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	Optional<License> saveLicense(@RequestBody License license) {
		return Optional.of(licenseService.saveLicense(license));
	}
	
	@RequestMapping(value = "/dummyins", method = RequestMethod.GET)
	License putDummyLicense() {
		License l = new License();
		l.withId("532652");
		return licenseService.saveLicense(l);
	}
	
	@RequestMapping(value="/getDummyLicense", method=RequestMethod.GET)
	Optional<License> getDummyLicense(){
		return Optional.of(new License());
	}
	
	@RequestMapping(value="/hw")
	String hw() {
		System.out.println(UserContextHolder.getContext().getCorrelationId());
		return "Helllo";
	}
	
}
