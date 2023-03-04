package com.cydeo.pojo_project;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.CharBuffer;
import java.util.*;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class GeneratePojo {

    public static <T> JsonPath sendThese(Map<String, T> pathParams, String endPoint) {
        JsonPath jsonPath = given().accept(ContentType.JSON).contentType(ContentType.JSON).pathParams(pathParams).when().get(endPoint).prettyPeek().then().extract().jsonPath();
        return jsonPath;
    }

    public static <T> JsonPath sendThese(String endPoint) {
        JsonPath jsonPath = given().accept(ContentType.JSON).contentType(ContentType.JSON).when().get(endPoint).prettyPeek().then().extract().jsonPath();
        return jsonPath;
    }

    static boolean isThereList = false;
    static boolean isThereMap = false;


    /**
     * Generates Pojo Files in Run_Time
     *
     * @param map
     * @param packageName
     * @param fileName
     */

    public static void from(Map<String, Object> map, String packageName, String fileName) {


        for (Object each : map.keySet()) {

            if (map.get(each) instanceof List) {
                isThereList = true;
            }
            if (map.get(each) instanceof Map) {
                isThereMap = true;
            }

        }
        makePojo(map, packageName, fileName);
    }

    /**
     * Replaces withe space with under_score and returns in lowerCase with no special characters
     *
     * @param name
     * @return String
     */

    private static String validatePackageName(String name) {
        if (name.length() < 3) {
            throw new NotValidPackageNameException("Package name must contain at least 3 letters");
        }
        String pack = "";
        name = name.trim();
        List<Character> letters = new ArrayList<>();
        for (int i = 0; i < name.length(); i++) {
            letters.add(name.charAt(i));
        }
        letters.removeIf(m -> !Character.isLetter(m) && m != ' ');
        for (Character a : letters) {
            if (a == ' ') {
                pack += '_';
            } else {
                pack += a;
            }
        }
        return pack.substring(0, 1).toLowerCase() + pack.substring(1);
    }


    private static int num = 1;

    /**
     * Creates Pojo Classes dynamically from Map
     *
     * @param map
     * @param packageName
     * @param fileName
     */

    private static void makePojo(Map<String, Object> map, String packageName, String fileName) {
        String classNameToCreated = validateClassName(fileName);
        packageName = validatePackageName(packageName);

        new File("src/test/java/com/cydeo/pojo_project/" + packageName).mkdir();

        String keyName;
        BufferedWriter writer = null;
        boolean exists = new File("src/test/java/com/cydeo/pojo_project/" + packageName + "/" + classNameToCreated + ".java").exists();

        File file = null;
        try {
            if (exists) {
                num++;
                classNameToCreated += num;
            }
            file = new File("src/test/java/com/cydeo/pojo_project/" + packageName + "/" + classNameToCreated + ".java");
            file.createNewFile();


        } catch (IOException e) {
            e.getMessage();
        }

        try {
            FileWriter fileWriter = new FileWriter(file);

            writer = new BufferedWriter(fileWriter);
            writer.write("package com.cydeo.pojo_project." + packageName + ";\n\n");
            writer.write("import com.fasterxml.jackson.annotation.JsonIgnoreProperties;\n");
            writer.write("import com.fasterxml.jackson.annotation.JsonProperty;\n");
            writer.write("import lombok.Data;\n\n");
            if (isThereList) {
                writer.write("import java.util.List;\n\n");
                isThereList = false;
            }

            writer.write("@Data\n");
            writer.write("@JsonIgnoreProperties(ignoreUnknown = true)\n");
            writer.write("public class " + classNameToCreated + " {\n\n");

            for (String eachOne : map.keySet()) {

                keyName =eachOne;


                Object entry = map.get(eachOne);

                if (entry instanceof String) {
                    writer.write("@JsonProperty(\"" + eachOne + "\")\n");
                    writer.write("private String " + validateReferenceName(keyName) + ";\n");
                }
                if (entry instanceof Integer) {
                    writer.write("@JsonProperty(\"" + eachOne + "\")\n");

                    writer.write("private int " + validateReferenceName(keyName) + ";\n");
                }
                if (entry instanceof List) {


                    exists = new File("src/test/java/com/cydeo/pojo_project/" + packageName + "/" + keyName + ".java").exists();
                    if (exists) {
                        keyName += num++;
                    }

                        if (checkThisList((List) entry)) {
                            String type = ((List<?>) entry).get(0).getClass().toString() ;
                            String typeName = type.substring(type.lastIndexOf(".")+1);
                            writer.write("@JsonProperty(\"" + eachOne + "\")\n");
                            writer.write("private List<" + typeName + "> " + validateReferenceName(eachOne) + ";\n");
                        } else {

                            from((Map<String, Object>) ((List<?>) entry).get(0), packageName, keyName);
                            writer.write("@JsonProperty(\"" + eachOne + "\")\n");


                            writer.write("private List<" + validateClassName(keyName) + "> " + validateReferenceName(eachOne) + ";\n");
                        }
                }
                if (entry instanceof Map) {
                    exists = new File("src/test/java/com/cydeo/pojo_project/" + packageName + "/" + validateClassName(keyName) + ".java").exists();

                    keyName = exists ? validateClassName(keyName)+ num++ : validateClassName(keyName);

                        writer.write("@JsonProperty(\"" + eachOne + "\")\n");
                        from((Map<String, Object>) entry, packageName, validateClassName(keyName));
                        writer.write("private " + validateClassName(keyName) + " " + validateReferenceName(eachOne) + ";\n");

                }

                if (entry instanceof Long) {
                    writer.write("@JsonProperty(\"" + eachOne + "\")\n");
                    writer.write("private long " + validateReferenceName(eachOne) + ";\n");
                }
                if (entry instanceof Double) {
                    writer.write("@JsonProperty(\"" + eachOne + "\")\n");
                    writer.write("private double " + validateReferenceName(eachOne) + ";\n");
                }

                if (entry instanceof Boolean) {
                    writer.write("@JsonProperty(\"" + eachOne + "\")\n");
                    writer.write("private boolean " + validateReferenceName(eachOne) + ";\n");
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
     *
     * @param str
     * @return String
     */

    private static String validateClassName(String str) {
        str = str.trim();
        int lastNum = 0;
        int i = 0;


        // Refactor the name if starting is not letter or there is no name

        if (!Character.isLetter(str.charAt(i))) {
            while (!Character.isLetter(str.charAt(i++))) {
                lastNum = i;
            }
            if (lastNum == str.length() - 1) {
                Scanner in = new Scanner(System.in);
                System.out.println("property name is not valid as variable name please enter valid name");
                String name = in.next();
                in.close();
                str = validateClassName(name);

            } else {
                str = str.substring(lastNum);
            }
        }

        // removes special characters from name
        char[] r = str.toCharArray();
        List<Character> list = CharBuffer.wrap(r).chars().mapToObj(h -> (char) h).collect(Collectors.toList());

        Iterator<Character> it = list.iterator();
        String o = "";

        while (it.hasNext()) {
            Character l = it.next();
            if (Character.isDigit(l) || Character.isLetter(l)) {
                o += l;
            }
        }
        str = o;

        // Making PascalCase
        str = pascalCase(str);
        return str;
    }

    private static String validateReferenceName(String name){
        String s = validateClassName(name);
        return s.substring(0,1).toLowerCase() + s.substring(1);

    }


    /**
     * formatting the given string to PascalCode
     *
     * @param str
     * @return String
     */

    private static String pascalCase(String str) {
        String[] s1 = str.split(" ");
        String d = "";
        for (String s : s1) {
            d += str.substring(0, 1).toUpperCase() + str.substring(1);
        }
        return d;
    }


}
