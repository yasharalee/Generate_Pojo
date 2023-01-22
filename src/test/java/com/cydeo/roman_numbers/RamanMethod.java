package com.cydeo.roman_numbers;

import java.util.*;

public class RamanMethod {

    public static String romanNumConvertor(int num) {
        Map<Integer, String> romNum = getMap("Integer");
        // 2959 = 2000 + 800 + 50 + 9 = (II)CMLIX

        String s;


        String StrMult = "1";
        String StrNum = "" + num;
        for (int i = 1; i < StrNum.length(); i++) {
            StrMult += "0";
        }
        int multNum = Integer.parseInt(StrMult);


        if (num <= 0) {
            return "";
        }

        if (romNum.containsKey(num) && num <= 1000) {
            return romNum.get(num);
        } else {
            //=============================================================

            if (num < 1_000 && !romNum.containsKey(num)) {

                return romNum.get((num / multNum) * multNum) + romanNumConvertor(num % multNum);

            } else

                //===============================================================

                if (num <= 999_999) {
                    return "(" + romNum.get((num - (num % multNum)) / 1000) + ")" + romanNumConvertor(num % multNum);

                } else

                    //==============================================================

                    if (num <= 1_000_000 && !romNum.containsKey(num)) {
                        return "(" + romNum.get(num / 1000) + ")" + romanNumConvertor(num % multNum);
                    } else {
                        s = " not supported";
                    }
            // ==================================================================

        }

        return s;
    }

    private static <T, E> Map<T, E> getMap(String keyAs) {


        Map<Integer, String> romNum = new LinkedHashMap<>(Map.of(
                1, "I",
                2, "II",
                3, "III",
                4, "IV",
                5, "V",
                6, "VI",
                7, "VII",
                8, "VIII",
                9, "IX"
        ));
        romNum.put(10, "X");
        romNum.put(20, "XX");
        romNum.put(30, "XXX");
        romNum.put(40, "XL");
        romNum.put(50, "L");
        romNum.put(60, "LX");
        romNum.put(70, "LXX");
        romNum.put(80, "LXXX");
        romNum.put(90, "XC");
        romNum.put(100, "C");
        romNum.put(200, "CC");
        romNum.put(300, "CCC");
        romNum.put(400, "CD");
        romNum.put(500, "D");
        romNum.put(600, "DC");
        romNum.put(700, "DCC");
        romNum.put(800, "DCCC");
        romNum.put(900, "CM");
        romNum.put(1000, "M");
        if (keyAs.equalsIgnoreCase("Integer")) {
            return (Map<T, E>) romNum;
        } else {
            Map<String, Integer> map = new HashMap<>();

            for (Integer key : romNum.keySet()) {
                map.put(romNum.get(key), key);
            }


            return (Map<T, E>) map;
        }

    }

    private static Map<String, Integer> map = getMap("String");

    public static int romanNumConvertor(String str) {
        if (str.length() == 0){
            return 0;
        }
        if (str.contains("(")) {
            String sub = str.substring(str.indexOf("(") + 1, str.indexOf(")"));
            String secSub = str.substring(str.indexOf(")") + 1);
            Integer s = (getValue(sub) * 1000);
            if (str.length() <= 4) {
                return s;
            } else {
                return s + romanNumConvertor(secSub);
            }

        } else {

            if (str.length() >= 2){
                if (getValue(str.charAt(1)) > getValue(str.charAt(0))) {
                    return getValue(str.charAt(1)) - getValue(str.charAt(0)) + romanNumConvertor(str.substring(2));
                } else {
                    return getValue(str.charAt(0)) + romanNumConvertor(str.substring(1));
                }
            }else {
                return getValue(str.charAt(0));
            }


        }
    }

    private static Integer getValue(String str) {

        if (str.length() > 1) {
            if (getValue(str.charAt(1)) > getValue(str.charAt(0))) {
                return getValue(str.charAt(1)) - getValue(str.charAt(0));
            } else {
                return getValue(str.charAt(0)) + getValue(str.charAt(1));
            }
        } else {
            return map.get(str);
        }

    }


    private static Integer getValue(char str) {

        return map.get("" + str);

    }



/*
          14 = XIV
          65 = LXV
        1015 = (I)XV
         104 = CIV
 */
    /*
     for (int i = 1; i < str.length(); i++) {
                int target = i - 1;
                Integer curr = getValue(str.charAt(i));
                Integer per = getValue(str.charAt(target));

                if (curr > per) {
                    return curr - per;
                } else {
                    return per + curr;
                }

            }
     */


}
