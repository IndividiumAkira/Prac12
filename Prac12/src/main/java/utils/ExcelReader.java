package utils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.Student;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader {
    public static List<org.example.Student> readStudentsFromExcel(String filePath) throws IOException {
        List<Student> students = new ArrayList<>();
        try (FileInputStream fileInputStream = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fileInputStream)) {

            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getRowNum() == 0) {
                    continue;
                }

                Cell nameCell = row.getCell(0);
                String name = nameCell.getStringCellValue();

                Cell currentScholarshipCell = row.getCell(1);
                double currentScholarship = getNumericValue(currentScholarshipCell);

                Cell newScholarshipCell = row.getCell(2);
                double newScholarship = getNumericValue(newScholarshipCell);

                students.add(new Student(name, currentScholarship, newScholarship));
            }
        }
        return students;
    }

    private static double getNumericValue(Cell cell) {
        if (cell.getCellType() == CellType.STRING) {
            return Double.parseDouble(cell.getStringCellValue().replace(" ", ""));
        } else if (cell.getCellType() == CellType.NUMERIC) {
            return cell.getNumericCellValue();
        } else {
            throw new IllegalStateException("Unexpected cell type");
        }
    }
}