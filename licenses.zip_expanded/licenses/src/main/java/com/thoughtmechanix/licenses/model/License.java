package com.thoughtmechanix.licenses.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "licenses")
public class License{
  @Id
  @Field("license_id")
  private String licenseId;

  @Field("organization_id")
  private String organizationId;

  @Field("product_name")
  private String productName;

  @Field("license_type")
  private String licenseType;

  @Field("license_max")
  private Integer licenseMax;

  @Field("license_allocated")
  private Integer licenseAllocated;

  @Field("comment")
  private String comment;


  public Integer getLicenseMax() {
    return licenseMax;
  }

  public void setLicenseMax(Integer licenseMax) {
    this.licenseMax = licenseMax;
  }

  public Integer getLicenseAllocated() {
    return licenseAllocated;
  }

  public void setLicenseAllocated(Integer licenseAllocated) {
    this.licenseAllocated = licenseAllocated;
  }


  public String getLicenseId() {
    return licenseId;
  }

  public void setLicenseId(String licenseId) {
    this.licenseId = licenseId;
  }

  public String getOrganizationId() {
    return organizationId;
  }

  public void setOrganizationId(String organizationId) {
    this.organizationId = organizationId;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(String productName) {
    this.productName = productName;
  }

  public String getLicenseType() {
    return licenseType;
  }

  public void setLicenseType(String licenseType) {
    this.licenseType = licenseType;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public License withId(String id){
    this.setLicenseId(id);
    return this;
  }

  public License withOrganizationId(String organizationId){
    this.setOrganizationId(organizationId);
    return this;
  }

  public License withProductName(String productName){
    this.setProductName(productName);
    return this;
  }

  public License withLicenseType(String licenseType){
    this.setLicenseType(licenseType);
    return this;
  }

  public License withLicenseMax(Integer licenseMax){
    this.setLicenseMax(licenseMax);
    return this;
  }

  public License withLicenseAllocated(Integer licenseAllocated){
    this.setLicenseAllocated(licenseAllocated);
    return this;
  }

  public License withComment(String comment){
    this.setComment(comment);
    return this;
  }



}