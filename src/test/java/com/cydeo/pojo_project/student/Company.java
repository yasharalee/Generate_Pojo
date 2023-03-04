package com.cydeo.pojo_project.student;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Company {

@JsonProperty("companyId")
private int companyId;
@JsonProperty("companyName")
private String companyName;
@JsonProperty("title")
private String title;
@JsonProperty("startDate")
private String startDate;
@JsonProperty("address")
private Address address;
}