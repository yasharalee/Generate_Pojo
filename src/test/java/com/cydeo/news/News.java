package com.cydeo.news;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class News {

private String status;
private int totalResults;
private List<Articles> articles;
}