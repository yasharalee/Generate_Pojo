package com.cydeo.pojo_project.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Geometry2 {

@JsonProperty("type")
private String type;
@JsonProperty("coordinates")
private List<Double> coordinates;
}