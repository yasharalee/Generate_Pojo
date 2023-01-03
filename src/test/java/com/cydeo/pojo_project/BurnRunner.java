package com.cydeo.pojo_project;


import io.restassured.path.json.JsonPath;

import java.util.Map;

import static com.cydeo.pojo_project.GeneratePojo.*;

public class BurnRunner {
    public static void main(String[] args) {

        //JsonPath jsonPath = sendThese("http://54.237.233.250:8000/api/spartans");

        JsonPath jsonPath = sendThese("http://54.237.233.250:1000/ords/hr/countries");
        Map<String, Object> jsToMap = jsonPath.getObject("", Map.class);
       //List<Map> list = jsonPath.getObject("", List.class);
       // System.out.println(list);
//
//        makePojo(list.get(0),"spartans","spartan");

        /**
        * following method will generate and add POJO files under "cydeo" package, dynamically
        * it is able to create List of custom Classes as well
        */
        from(jsToMap, "Countries", "Countries");

//        Hr hr = jsonPath.getObject("", Hr.class);
//        System.out.println("hr = " + hr);


    }
}
