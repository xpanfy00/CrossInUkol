package com.demo;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileValidator {

    public static Path validate(String[] args) {
        if (args.length == 0) {
            System.out.println("Error: Please provide the path to an Excel file as an argument.");
            return null;
        }

        Path path = Paths.get(args[0]);
        if (!Files.exists(path) || !Files.isRegularFile(path)) {
            System.out.println("Error: File does not exist or is not a regular file.");
            return null;
        }

        String fileName = path.getFileName().toString().toLowerCase();
        if (!(fileName.endsWith(".xls") || fileName.endsWith(".xlsx"))) {
            System.out.println("Error: Only .xls and .xlsx files are supported.");
            return null;
        }

        return path;
    }
}
