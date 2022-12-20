package com.cydeo.db_to_excel;

public class RunnerExcel {
    public static void main(String[] args) {
        fileWriter cn = new fileWriter("jdbc:oracle:thin:@54.237.233.250:1521:XE", "hr", "hr");

        cn.runQuery("select FIRST_NAME || ' ' || LAST_NAME as full_Name, DEPARTMENT_NAME from\n" + "    EMPLOYEES E inner join DEPARTMENTS D\n" + "on D.DEPARTMENT_ID = E.DEPARTMENT_ID");
        cn.writeToExcel("employees", "empDep");
    }
}
