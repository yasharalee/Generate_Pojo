package com.cydeo.pojo_project.student;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Address {

@JsonProperty("addressId")
private int addressId;
@JsonProperty("street")
private String street;
@JsonProperty("city")
private String city;
@JsonProperty("state")
private String state;
@JsonProperty("zipCode")
private int zipCode;
}