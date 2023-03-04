package com.cydeo.pojo_project.countries;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Items {

@JsonProperty("country_id")
private String countryId;
@JsonProperty("country_name")
private String countryName;
@JsonProperty("region_id")
private int regionId;
@JsonProperty("links")
private List<Links> links;
}