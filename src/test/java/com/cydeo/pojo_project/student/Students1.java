package com.cydeo.pojo_project.student;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Students1 {

@JsonProperty("studentId")
private int studentId;
@JsonProperty("firstName")
private String firstName;
@JsonProperty("lastName")
private String lastName;
@JsonProperty("batch")
private int batch;
@JsonProperty("joinDate")
private String joinDate;
@JsonProperty("birthDate")
private String birthDate;
@JsonProperty("password")
private String password;
@JsonProperty("subject")
private String subject;
@JsonProperty("gender")
private String gender;
@JsonProperty("admissionNo")
private String admissionNo;
@JsonProperty("major")
private String major;
@JsonProperty("section")
private String section;
@JsonProperty("contact")
private Contact contact;
@JsonProperty("company")
private Company company;
}