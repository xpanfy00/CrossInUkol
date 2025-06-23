package com.demo;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader {

    public static List<String> readSheet(String fileName) throws IOException {
        List<String> values = new ArrayList<>();

        try (FileInputStream fileInputStream = new FileInputStream(fileName);
             Workbook workbook = createWorkbook(fileInputStream, fileName)) {

            Sheet sheet = workbook.getSheetAt(0);
            DataFormatter formatter = new DataFormatter();

            Row header = sheet.getRow(0);
            if(header == null){
                System.out.println("Sheet does not contain header");
            }
            List<Integer> dataColumnIndexes = new ArrayList<>();

            for(Cell cell : header) {
                String cellValue = formatter.formatCellValue(cell).trim();
                if("data".equalsIgnoreCase(cellValue)) {
                    dataColumnIndexes.add(cell.getColumnIndex());
                }
            }

            if (dataColumnIndexes.isEmpty()) {
                System.out.println("No column with header 'Data' found.");
            }

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                for (int colIndex : dataColumnIndexes) {
                    Cell cell = row.getCell(colIndex);
                    if (cell != null) {
                        String value = formatter.formatCellValue(cell).trim();

                        try {
                            long number = Long.parseLong(value);
                            if (number > 0) {
                                values.add(value);
                            }
                        } catch (NumberFormatException ignored) {

                        }
                    }
                }
            }
        }

        return values;
    }

    private static Workbook createWorkbook(InputStream inputStream, String fileName) throws IOException {
        if (fileName.toLowerCase().endsWith(".xlsx")) {
            return new XSSFWorkbook(inputStream);
        } else if (fileName.toLowerCase().endsWith(".xls")) {
            return new HSSFWorkbook(inputStream);
        } else {
            throw new IllegalArgumentException("Unsupported file type: " + fileName);
        }
    }

}
