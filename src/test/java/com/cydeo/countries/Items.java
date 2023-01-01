package com.cydeo.countries;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Items {

@JsonProperty("country_id")
private String country_id;
@JsonProperty("country_name")
private String country_name;
@JsonProperty("region_id")
private int region_id;
@JsonProperty("links")
private List<Links> links;
}