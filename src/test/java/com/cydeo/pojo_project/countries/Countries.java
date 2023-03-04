package com.cydeo.pojo_project.countries;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Countries {

@JsonProperty("items")
private List<Items> items;
@JsonProperty("hasMore")
private boolean hasMore;
@JsonProperty("limit")
private int limit;
@JsonProperty("offset")
private int offset;
@JsonProperty("count")
private int count;
@JsonProperty("links")
private List<Links1> links;
}