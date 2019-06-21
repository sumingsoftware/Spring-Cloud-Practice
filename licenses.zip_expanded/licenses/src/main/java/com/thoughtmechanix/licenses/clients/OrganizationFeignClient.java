package com.thoughtmechanix.licenses.clients;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.thoughtmechanix.licenses.model.Organization;

@FeignClient("organizationservice")
public interface OrganizationFeignClient {
		@RequestMapping(value="/v1/organizations/{organizationId}", method=RequestMethod.GET, consumes="application/json")
		Optional<Organization> getOrganization(@PathVariable("organizationId") String organizationId);
		
}
 