package com.cydeo.pojo_project;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

import java.util.Map;

public class FourthRunner {
    public static void main(String[] args) {

        JsonPath jp = RestAssured.given().accept(ContentType.JSON).contentType(ContentType.JSON)
                .when().get("https://newsapi.org/v2/everything?q=bitcoin&apiKey=f83c7864f4024e21a064bf59f5d992b1").prettyPeek()
                .then().extract().jsonPath();

        Map<String, Object> map = jp.getObject("", Map.class);
       GeneratePojo.from(map,"news","News");


    }
}
