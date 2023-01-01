package com.cydeo;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.CharBuffer;
import java.util.*;
import java.util.stream.Stream;

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

    /**
     *  Creates Pojo Classes dynamically from Map
     * @param map
     * @param packageName
     * @param fileName
     */

    public static void makePojo(Map<String, Object> map, String packageName, String fileName) {
        String classNameToCreated = new String(validateName(fileName,true));

        new File("src/test/java/com/cydeo/" + packageName).mkdir();

        String keyName;
        BufferedWriter writer = null;
        boolean exists = new File("src/test/java/com/cydeo/"+packageName+"/" + classNameToCreated + ".java").exists();

        File file = null;
        try {
            if (exists) {
                num++;
                classNameToCreated += num;
            }
            file = new File("src/test/java/com/cydeo/" + packageName + "/" + classNameToCreated + ".java");
            file.createNewFile();


        } catch (IOException e) {
            e.getMessage();
        }

        try {
            FileWriter fileWriter = new FileWriter(file);

            writer = new BufferedWriter(fileWriter);
            writer.write("package com.cydeo." + packageName + ";\n\n");
            writer.write("import com.fasterxml.jackson.annotation.JsonIgnoreProperties;\n");
            writer.write("import com.fasterxml.jackson.annotation.JsonProperty;\n");
            writer.write("import lombok.Data;\n\n");
            writer.write("import java.util.List;\n\n");
            writer.write("@Data\n");
            writer.write("@JsonIgnoreProperties(ignoreUnknown = true)\n");
            writer.write("public class " + classNameToCreated + " {\n\n");

            for (String eachOne : map.keySet()) {

                keyName = eachOne;


                Object entry = map.get(eachOne);

                if (entry instanceof String) {
                    writer.write("@JsonProperty(\""+eachOne+"\")\n");
                    writer.write("private String " + keyName + ";\n");
                }
                if (entry instanceof Integer) {
                    writer.write("@JsonProperty(\""+eachOne+"\")\n");

                    writer.write("private int " + keyName + ";\n");
                }
                if (entry instanceof List) {


                    exists = new File("src/test/java/com/cydeo/" + packageName + "/" + keyName + ".java").exists();
                    if (exists) {
                        keyName += num++;
                    }

                    if (checkThisList((List) entry)) {
                        writer.write("@JsonProperty(\""+eachOne+"\")\n");
                        writer.write("private List<" + classNameToCreated + "> " + keyName + ";\n");
                    } else {


                        makePojo((Map<String, Object>) ((List<?>) entry).get(0), packageName, keyName);
                        writer.write("@JsonProperty(\""+eachOne+"\")\n");
                        writer.write("private List<" + validateName(keyName,true) + "> " + keyName + ";\n");
                    }


                }
                if (entry instanceof Map) {
                    writer.write("@JsonProperty(\""+eachOne+"\")\n");
                    makePojo((Map<String, Object>) entry, packageName,validateName(keyName,true));
                    writer.write("private " + validateName(keyName,true)+" " + keyName + ";\n");
                }

                if (entry instanceof Long) {
                    writer.write("@JsonProperty(\""+eachOne+"\")\n");
                    writer.write("private long " + keyName + ";\n");
                }
                if (entry instanceof Double) {
                    writer.write("@JsonProperty(\""+eachOne+"\")\n");
                    writer.write("private double " + keyName + ";\n");
                }

                if (entry instanceof Boolean) {
                    writer.write("@JsonProperty(\""+eachOne+"\")\n");
                    writer.write("private boolean " + keyName + ";\n");
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

    /**
     * Maintains Naming Convention for POJO files
     * @param str
     * @param isItClassName
     * @return String
     */

    private static String validateName(String str, boolean isItClassName) {
        str = str.trim();
        int lastNum = 0;
        int i = 0;


        // Making PascalCase

        if (isItClassName){
            str = pascalCase(str);
        }else {
            str = str.toLowerCase().replace(" ","_");
        }

        // Remove the white space

        if (str.contains(" ")) {
            String[] temp = str.split(" ");
            String dum = "";
            if (isItClassName) {
                for (String s : temp) {
                    dum += pascalCase(s);
                }
            }else {
                dum += temp[0];
                for (int t = 1; t < temp.length; t++) {
                    dum += pascalCase(temp[t]);
                }
            }
            str = dum;
        }


        // Refactor the name if starting is not letter

        if (!Character.isLetter(str.charAt(0))) {
            while (!Character.isLetter(str.charAt(i++))) {
                lastNum = i;
            }
            if (lastNum == str.length() - 1) {
                Scanner in = new Scanner(System.in);
                System.out.println("property name is not valid as variable name please enter valid name");
                String name = in.next();
                in.close();
                str = validateName(name, isItClassName);

            } else {
                str = str.substring(lastNum);
            }
        }

        // removes special characters from name
//        char[] r = str.toCharArray();
//        Stream<Character> list = CharBuffer.wrap(r).chars().mapToObj(h -> (char)h);
//
//        Iterator<Character> it = list.iterator();
//
//        while (it.hasNext()){
//            Character l = it.next();
//            if (!Character.isDigit(l) || !Character.isLetter(l)) {
//                it.remove();
//            }
//        }
//
//        System.out.println(list);

        return str;
    }



    /**
     * formatting the given string to PascalCode
     * @param str
     * @return String
     */

    private static String pascalCase(String str) {
        str = str.trim();
        String s = str.substring(0, 1).toUpperCase() + str.substring(1);
        return s;
    }


}
