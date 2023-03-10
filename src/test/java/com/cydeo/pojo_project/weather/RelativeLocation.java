package com.cydeo.pojo_project.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RelativeLocation {

@JsonProperty("type")
private String type;
@JsonProperty("geometry")
private Geometry1 geometry;
@JsonProperty("properties")
private Properties2 properties;
}