package com.cydeo.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Hr {

private List<Items> items;
private boolean hasMore;
private int limit;
private int offset;
private int count;
private List<Links1> links;
}