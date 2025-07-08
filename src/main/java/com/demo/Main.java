package com.demo;

import java.io.IOException;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws IOException {

        if (args.length != 1) {
            System.out.println("Usage: java -jar CrossInUkol-1.0.0-jar-with-dependencies.jar <path-to-excel-file>");
            return;
        }

        ExcelReader excelReader = new ExcelReader();
        Path filePath = FileValidator.validate(args);

        if (filePath != null) {
            for (var value : excelReader.readSheet(args[0])) {
                if (PrimeChecker.isPrime(Integer.parseInt(value))){
                    System.out.println(value);
                }

            }
        }



    }
}