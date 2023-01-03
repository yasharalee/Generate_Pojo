package com.cydeo.pojo_project;

import com.cydeo.pojo_project.GeneratePojo;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class ThirdRunner {
    public static void main(String[] args) {

        JsonPath jsonPath = given().accept(ContentType.JSON)
                .pathParams("id",4)
                .when().get("https://api.training.cydeo.com/student/{id}")
                .prettyPeek().then().extract().jsonPath();



        Map<String, Object> map2 = jsonPath.getObject("", Map.class);
        System.out.println("--------------------------------------------------------");
        System.out.println(map2);

        GeneratePojo.from(map2, "Student", "Students");


    }
}
