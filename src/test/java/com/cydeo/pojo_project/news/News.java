package com.cydeo.pojo_project.news;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class News {

@JsonProperty("status")
private String status;
@JsonProperty("totalResults")
private int totalResults;
@JsonProperty("articles")
private List<News> articles;
}