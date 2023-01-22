package com.cydeo.roman_numbers;

public class RomanNumber {
    public static void main(String[] args) {

        // numbers greater than 1_000_000 are not supported
//
        System.out.println("14: " + RamanMethod.romanNumConvertor(14));
        System.out.println("65: " + RamanMethod.romanNumConvertor(65));
        System.out.println("1015: " + RamanMethod.romanNumConvertor(1015));
        System.out.println("104: " + RamanMethod.romanNumConvertor(104));
        System.out.println("6000: " + RamanMethod.romanNumConvertor(60000));
        System.out.println(RamanMethod.romanNumConvertor(("XIV")));
        System.out.println(RamanMethod.romanNumConvertor(("LXV")));
        System.out.println(RamanMethod.romanNumConvertor("(I)XV"));
        System.out.println(RamanMethod.romanNumConvertor("CIV"));
        System.out.println(RamanMethod.romanNumConvertor("(LX)"));

    }

}












//    public static String romanNumConvertor(int num) {
//        String s = "";  // 1    5    10   50  100  500  1000
//        String[] RNums = {"I", "V", "X", "L", "C", "D", "M"};
//        if (num == 0) return "N/A";
//
//        String numStr = num + ""; // converting num to String
//        String dividerStr = "1"; // divider as String
//        String[] numSArr = numStr.split(""); // array of digits as String
//
//        for (int i = 0; i < numSArr.length - 1; i++) {
//            dividerStr += "0";
//        }
//        int dividerNUm = Integer.parseInt(dividerStr); // divider as int
//
//
//
//        return s;
//    }















//        I             1
//        V             5
//        X             10
//        L             50
//        C             100
//        D             500
//        M             1000

//        I can be placed before V (5) and X (10) to make 4 and 9.
//        X can be placed before L (50) and C (100) to make 40 and 90.
//        C can be placed before D (500) and M (1000) to make 400 and 900.


//
//    String dividerStr = "1"; // divider as String
//    String numStr = num + ""; // converting num to String
//    String[] numSArr = numStr.split(""); // array of digits as String

//
//        for (int i = 0; i < numSArr.length -1 ; i++) {
//            dividerStr += "0";
//        }
//        int dividerNUm = Integer.parseInt(dividerStr); // divider as int


//  int DThousand = num/1000;
//        int DFiveHundred = num/500;
//        int DHundred = num/100;
//        int DFifty = num/50;
//        int DTen = num/10;
//        int DFive = num/5;
//
//        if (DThousand > 0){
//
//        }


//if (num == 1){
//        return "I";
//        }else if (num == 2){
//        return "II";
//        }else if (num == 3){
//        return "III";
//        }else if (num == 4){
//        return "IV";
//        }else if (num == 5){
//        return "V";
//        }else if (num == 6){
//        return "VI";
//        } else if (num == 7) {
//        return "VII";
//        } else if (num == 8) {
//        return "VIII";
//        } else if (num == 9) {
//        return "IX";
//        }