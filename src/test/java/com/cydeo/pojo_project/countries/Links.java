package com.cydeo.pojo_project.countries;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Links {

@JsonProperty("rel")
private String rel;
@JsonProperty("href")
private String href;
}