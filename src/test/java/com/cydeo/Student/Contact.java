package com.cydeo.Student;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Contact {

@JsonProperty("contactId")
private int contactId;
@JsonProperty("phone")
private String phone;
@JsonProperty("emailAddress")
private String emailAddress;
@JsonProperty("permanentAddress")
private String permanentAddress;
}