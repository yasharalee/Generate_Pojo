package com.cydeo.pojo_project.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Bearing {

@JsonProperty("unitCode")
private String unitCode;
@JsonProperty("value")
private int value;
}