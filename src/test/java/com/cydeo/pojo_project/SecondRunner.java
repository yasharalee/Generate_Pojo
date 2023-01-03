package com.cydeo.pojo_project;

import com.cydeo.pojo_project.GeneratePojo;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class SecondRunner {
    public static void main(String[] args) {
        Map<String,String> map = new LinkedHashMap<>(Map.of(
                "Latitude","45.4914684",
                "Longitude","-122.8014368"

        ));

        JsonPath jp = given().accept(ContentType.JSON)
                .when().get("https://api.weather.gov/points/" +map.get("Latitude") + "," + map.get("Longitude")).prettyPeek()
                .then().statusCode(200).extract().jsonPath();
        Map<String,Object> map1 = jp.getObject("", Map.class);
//
//        for (Object each : map1.keySet()) {
//            System.out.println(each);
//        }


//        JsonPath jsonPath = given().accept(ContentType.JSON)
//                .when().get("https://newsapi.org/v2/everything?q=bitcoin&apiKey=f83c7864f4024e21a064bf59f5d992b1")
//                .prettyPeek().then().extract().jsonPath();



        Map<String, Object> map2 = jp.getObject("", Map.class);
        System.out.println("--------------------------------------------------------");
        System.out.println(map2);

       GeneratePojo.from(map2, "Weather", "weather");


    }
}
