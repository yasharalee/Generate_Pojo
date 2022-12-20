package com.cydeo;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

import java.io.*;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class BurnToClass {

    public static <T> JsonPath sendThese(Map<String, T> pathParams, String endPoint) {
        JsonPath jsonPath = given().accept(ContentType.JSON).contentType(ContentType.JSON).pathParams(pathParams).when().get(endPoint).prettyPeek().then().extract().jsonPath();
        return jsonPath;
    }

    public static <T> JsonPath sendThese(String endPoint) {
        JsonPath jsonPath = given().accept(ContentType.JSON).contentType(ContentType.JSON).when().get(endPoint).prettyPeek().then().extract().jsonPath();
        return jsonPath;
    }

    public static void makePojo(Map<String, Object> map, String path) {

        String className ;


        File file = new File("src/test/java/com/cydeo/" + path + ".java");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.getMessage();
        }

        try {
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            writer.write("package com.cydeo;\n\n");
            writer.write("import com.fasterxml.jackson.annotation.JsonIgnoreProperties;\n");
            writer.write("import lombok.Data;\n\n");
            writer.write("import java.util.List;\n\n");
            writer.write("@Data\n");
            writer.write("@JsonIgnoreProperties(ignoreUnknown = true)\n");
            writer.write("public class " + path + "{\n\n");

            for (String each : map.keySet()) {

                Object entry = map.get(each);

                if (entry instanceof String) {
                    writer.write("private String " + each + ";\n");
                }
                if (entry instanceof Integer) {
                    writer.write("private int " + each + ";\n");
                }
                if (entry instanceof List) {
                    className = (each.substring(0,1).toUpperCase())+(each.substring(1).toLowerCase());

                    if (checkThisList((List) entry)) {
                        writer.write("private List<" + ((List<?>) entry).get(0).getClass().toString().replace("class java.lang.", "") + "> " + className + ";\n");
                    }else {
                        makePojo((Map<String, Object>) ((List<?>) entry).get(0), className+"Class");
                        writer.write("private List<"+ className + "Class" + ">" + each+";\n" );
                    }


                }
                if (entry instanceof Long) {
                    writer.write("private long " + each + ";\n");
                }
                if (entry instanceof Double) {
                    writer.write("private double " + each + ";\n");
                }

                if (entry instanceof Boolean) {
                    writer.write("private boolean " + each + ";\n");
                }


            }

            writer.write("}");


            writer.close();

        } catch (FileNotFoundException e) {
            e.getMessage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    private static boolean checkThisList(List list) {
        boolean is = false;

        if (list.get(0) instanceof String || list.get(0) instanceof Integer || list.get(0) instanceof Byte || list.get(0) instanceof Short || list.get(0) instanceof Long || list.get(0) instanceof Float || list.get(0) instanceof Double || list.get(0) instanceof Character || list.get(0) instanceof Boolean) {
            is = true;
        }
        return is;
    }


}
