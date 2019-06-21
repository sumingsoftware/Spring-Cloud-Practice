package com.thoughtmechanix.licenses.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.thoughtmechanix.licenses.model.License;
import com.thoughtmechanix.licenses.repository.LicenseRepository;

@Service
public class LicenseService {
	@Autowired
	private LicenseRepository licenseRepository;

	public  License saveLicense(License license) {
//		license.withId(UUID.randomUUID().toString());

		return licenseRepository.save(license);

	}
	private void randaonSleep() {
		Random rand = new Random();
		int randInt = rand.nextInt(3)+1;
		if(randInt==3)sleep();
	}
	private void sleep() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(11000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@HystrixCommand(
			commandProperties=
			{@HystrixProperty(
					name="execution.isolation.thread.timeoutInMilliseconds",
					value="5000"),
			 @HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value="10"),
			 @HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value="75"),
			 @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds", value="7000"),
			 @HystrixProperty(name="metrics.rollingStats.timeInMilliseconds", value="15000"),
			 @HystrixProperty(name="metrics.rollingStats.numBuckets", value="5")
			},
			fallbackMethod="buildFallbackLicenseList",
			threadPoolKey="licenseByOrgIdThreadPool",
			threadPoolProperties= {
					@HystrixProperty(name="coreSize", value="30"),
					@HystrixProperty(name="maxQueueSize", value="10")
			}
			)
	public List<License> getLicenseByOrg(String orgId){
		randaonSleep();
		return licenseRepository.findByOrganizationId(orgId);
	}
	
	
	private List<License> buildFallbackLicenseList(String organizationId){
		List<License> fallbackList = new ArrayList<>();
		License license = new License()
		.withId("0000000-00-00000")
		.withOrganizationId( organizationId )
		.withProductName(
		"Sorry no licensing information currently available");
		fallbackList.add(license);
		return fallbackList;
		}
	
	public List<License> getLicenses() {
		return licenseRepository.findAll();
	}

	public void updateLicense(License license) {
		licenseRepository.save(license);
	}

	public void deleteLicense(License license) {
		licenseRepository.delete(license);
	}
}
