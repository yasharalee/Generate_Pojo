package com.cydeo.pojo_project.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather {

@JsonProperty("@context")
private List<String> context;
@JsonProperty("id")
private String id;
@JsonProperty("type")
private String type;
@JsonProperty("geometry")
private Geometry geometry;
@JsonProperty("properties")
private Properties properties;
}