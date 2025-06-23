package com.demo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {

        ExcelReader excelReader = new ExcelReader();
        Path filePath = FileValidator.validate(args);

        if (filePath != null) {
            for (var value : excelReader.readSheet(args[0])) {
                System.out.println(value);
            }
        }



    }
}