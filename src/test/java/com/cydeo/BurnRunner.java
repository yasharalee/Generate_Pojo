package com.cydeo;

import io.restassured.path.json.JsonPath;
import joptsimple.internal.Classes;

import java.util.Map;

import static com.cydeo.BurnToClass.*;

public class BurnRunner {
    public static void main(String[] args) {

        JsonPath jsonPath = sendThese("http://54.237.233.250:1000/ords/hr/countries");
        Map<String, Object> jsToMap = jsonPath.getObject("", Map.class);

        /**
        * following method will generate and add POJO files under "cydeo" package, dynamically
        * it is able to create List of custom Classes as well
        */
        makePojo(jsToMap, "hr");


    }
}
