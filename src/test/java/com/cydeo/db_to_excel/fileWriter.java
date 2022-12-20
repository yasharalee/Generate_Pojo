package com.cydeo.db_to_excel;


import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;


public class fileWriter {
    private Connection conn;
    private Statement stm;
    private ResultSet rs = null;
    private ResultSetMetaData rsmeta;
    private int last = 0;
    private int colCount = 0;

    public fileWriter(String url, String userName, String password) {


        try {
            conn = DriverManager.getConnection(url, userName, password);
            stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        } catch (SQLException e) {
            System.out.println("in sql " + e.getMessage());
        }
    }


    public void runQuery(String query) {
        try {
            rs = stm.executeQuery(query);
            rsmeta = rs.getMetaData();
            rs.last();
            last = rs.getRow();
            rs.beforeFirst();
            colCount = rsmeta.getColumnCount();
        } catch (SQLException e) {
            System.out.println("DURING QUERY : " + e.getMessage());
        }
    }


    public void writeToExcel(String filePath, String sheetName) {
        FileOutputStream out = null;
        XSSFWorkbook wb = null;
        try {
            wb = new XSSFWorkbook();
            XSSFSheet sheet = wb.createSheet(sheetName);
            XSSFRow row1 = sheet.createRow(0);
            XSSFFont bold = wb.createFont();
            bold.setBold(true);

            CellStyle clStyle = wb.createCellStyle();
            clStyle.setWrapText(true);
            clStyle.setAlignment(HorizontalAlignment.LEFT);

            CellStyle bb = wb.createCellStyle();
            bb.setWrapText(true);
            bb.setAlignment(HorizontalAlignment.LEFT);
            bb.setFont(bold);

            for (int j = 0; j < colCount; j++) {
                XSSFCell cell = row1.createCell(j + 1);
                cell.setCellValue(rsmeta.getColumnName(j + 1));
                cell.setCellStyle(bb);
            }

            int rowcount = 0;
            XSSFCell cell = null;
            while (rs.next()) {
                XSSFRow row = sheet.createRow((rowcount++) + 1);
                XSSFCell n = row.createCell(0);
                n.setCellValue(rowcount);
                n.setCellStyle(clStyle);

                for (int i = 1; i <= colCount; i++) {
                    cell = row.createCell(i);
                    String cTN = rsmeta.getColumnTypeName(i);
                    if (cTN.contains("NUMBER") || cTN.contains("INTEGER") || cTN.contains("int") || cTN.contains("DEC") || cTN.contains("DOUBLE") || cTN.contains("FLOAT")) {
                        cell.setCellValue(rs.getLong(i));
                    } else if (cTN.contains("DATE")) {
                        cell.setCellValue(rs.getDate(i));
                    } else {
                        cell.setCellValue(rs.getString(i));
                    }
                    cell.setCellStyle(clStyle);
                }
            }

            assert cell != null;
            for (int i = 0; i <= colCount + 1; i++) {
                sheet.autoSizeColumn(i);
            }

            out = new FileOutputStream(filePath + ".xlsx");
            wb.write(out);

        } catch (Exception e) {
            System.out.println("in apachi " + e.getMessage());
        } finally {

            try {
                out.close();
                wb.close();
            } catch (IOException e) {
                e.getMessage();
            }

        }
    }


}
