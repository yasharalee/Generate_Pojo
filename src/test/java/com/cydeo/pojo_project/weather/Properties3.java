package com.cydeo.pojo_project.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Properties3 {

@JsonProperty("city")
private String city;
@JsonProperty("state")
private String state;
@JsonProperty("distance")
private Distance distance;
@JsonProperty("bearing")
private Bearing bearing;
}