package com.cydeo;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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

    static int num = 1;

    public static void makePojo(Map<String, Object> map, String pathToFile, String packageName) {
        String path = new String(pathToFile);

        new File("src/test/java/com/cydeo/" + packageName).mkdir();

        String className;
        BufferedWriter writer = null;
        boolean exists = new File("src/test/java/com/cydeo/pojos/" + path + ".java").exists();

        File file = null;
        try {
            if (exists) {
                num++;
                path += num;
            }
            file = new File("src/test/java/com/cydeo/pojos/" + path + ".java");
            file.createNewFile();


        } catch (IOException e) {
            e.getMessage();
        }

        try {
            FileWriter fileWriter = new FileWriter(file);

            writer = new BufferedWriter(fileWriter);
            writer.write("package com.cydeo." + packageName + ";\n\n");
            writer.write("import com.fasterxml.jackson.annotation.JsonIgnoreProperties;\n");
            writer.write("import lombok.Data;\n\n");
            writer.write("import java.util.List;\n\n");
            writer.write("@Data\n");
            writer.write("@JsonIgnoreProperties(ignoreUnknown = true)\n");
            writer.write("public class " + path + " {\n\n");

            for (String each : map.keySet()) {

                Object entry = map.get(each);

                if (entry instanceof String) {
                    writer.write("private String " + each + ";\n");
                }
                if (entry instanceof Integer) {
                    writer.write("private int " + each + ";\n");
                }
                if (entry instanceof List) {
                    className = (each.substring(0, 1).toUpperCase()) + (each.substring(1).toLowerCase());
                    String entryName = ((List<?>) entry).get(0).getClass().toString();
                    // entryName.substring(entryName.lastIndexOf(".")+1)
                    exists = new File("src/test/java/com/cydeo/pojos/" + className + ".java").exists();
                    if (exists){
                        className += num++;
                    }

                    if (checkThisList((List) entry)) {
                        writer.write("private List<" + path + "> " + className + ";\n");
                    } else {


                        makePojo(
                                (Map<String, Object>) ((List<?>) entry).get(0),
                                className + "",
                                packageName);

                        writer.write("private List<" + className  + "> " + each + ";\n");
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

        } catch (IOException e) {
            e.getMessage();
        } finally {
            try {
                assert writer != null;
                writer.close();
            } catch (IOException e) {
                e.getMessage();
            }
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
