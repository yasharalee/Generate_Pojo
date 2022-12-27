package com.cydeo.news;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Articles {

private Map<String, Object> source;
private String author;
private String title;
private String description;
private String url;
private String urlToImage;
private String publishedAt;
private String content;
}