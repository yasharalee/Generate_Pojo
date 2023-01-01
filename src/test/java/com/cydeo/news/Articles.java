package com.cydeo.news;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Articles {

@JsonProperty("source")
private Source source;
@JsonProperty("author")
private String author;
@JsonProperty("title")
private String title;
@JsonProperty("description")
private String description;
@JsonProperty("url")
private String url;
@JsonProperty("urlToImage")
private String urlToImage;
@JsonProperty("publishedAt")
private String publishedAt;
@JsonProperty("content")
private String content;
}