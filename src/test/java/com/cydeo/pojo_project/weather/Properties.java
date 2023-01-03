package com.cydeo.pojo_project.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Properties {

@JsonProperty("@id")
private String id;
@JsonProperty("@type")
private String type;
@JsonProperty("cwa")
private String cwa;
@JsonProperty("forecastOffice")
private String forecastOffice;
@JsonProperty("gridId")
private String gridId;
@JsonProperty("gridX")
private int gridX;
@JsonProperty("gridY")
private int gridY;
@JsonProperty("forecast")
private String forecast;
@JsonProperty("forecastHourly")
private String forecastHourly;
@JsonProperty("forecastGridData")
private String forecastGridData;
@JsonProperty("observationStations")
private String observationStations;
@JsonProperty("relativeLocation")
private RelativeLocation relativeLocation;
@JsonProperty("forecastZone")
private String forecastZone;
@JsonProperty("county")
private String county;
@JsonProperty("fireWeatherZone")
private String fireWeatherZone;
@JsonProperty("timeZone")
private String timeZone;
@JsonProperty("radarStation")
private String radarStation;
}