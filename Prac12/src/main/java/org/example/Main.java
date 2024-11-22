package org.example;
import utils.ExcelReader;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[]args){
        String filePath ="students.xlsx";
        try {
            List<Student> students=ExcelReader.readStudentsFromExcel(filePath);

            for (Student student: students){
                System.out.println("Name: " + student.getName());
                System.out.println("Current Scholarship: " + student.getCurrentScholarship());
                System.out.println("New Scholarship: " + student.getNewScholarship());
                System.out.println("Scholarship Increase: " + student.getScholarshipIncrease());
                System.out.println();
            }
        } catch (IOException e) {
            System.out.println("Error reading Excel file: " +e.getMessage());
        }
    }
}