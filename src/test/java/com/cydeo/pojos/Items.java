package com.cydeo.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Items {

private String country_id;
private String country_name;
private int region_id;
private List<Links> links;
}