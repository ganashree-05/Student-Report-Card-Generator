package comreport;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.FileOutputStream;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class GradeManager {
    static List<Student> studentList = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== Student Grade Report Menu =====");
            System.out.println("1. Add Student Details");
            System.out.println("2. Display All Students");
            System.out.println("3. Generate Report as PDF");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> displayStudents();
                case 3 -> generatePDFReport();
                case 4 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void addStudent() {
        System.out.print("Enter Student Name: ");
        sc.nextLine();
        String name = sc.nextLine();

        System.out.print("Enter Roll Number: ");
        int roll = sc.nextInt();

        sc.nextLine(); // consume newline
        System.out.print("Enter Class/Standard: ");
        String standard = sc.nextLine();

        Map<String, Integer> marks = new LinkedHashMap<>();
        System.out.print("Enter number of subjects: ");
        int subCount = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < subCount; i++) {
            System.out.print("Enter subject name: ");
            String subject = sc.nextLine();
            System.out.print("Enter marks for " + subject + ": ");
            int mark = sc.nextInt();
            sc.nextLine();
            marks.put(subject, mark);
        }

        Student s = new Student(name, roll, standard, marks);
        studentList.add(s);
        System.out.println("Student added successfully!");
    }

    private static void displayStudents() {
        for (Student s : studentList) {
            System.out.println("\n===========================");
            System.out.println("Name: " + s.getName());
            System.out.println("Roll: " + s.getRoll());
            System.out.println("Class: " + s.getStandard());
            System.out.println("Marks: " + s.getMarks());
            System.out.println("Average: " + s.getAverage());
            System.out.println("Grade: " + s.getGrade());
            System.out.println("Remark: " + s.getRemark());
        }
    }

    private static void generatePDFReport() {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("StudentReportCard.pdf"));
            document.open();

            Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
            Font normalFont = new Font(Font.FontFamily.TIMES_ROMAN, 12);

            Paragraph title = new Paragraph("STUDENT REPORT CARD\n\n", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            for (Student s : studentList) {
                document.add(new Paragraph("Name     : " + s.getName(), normalFont));
                document.add(new Paragraph("Roll No  : " + s.getRoll(), normalFont));
                document.add(new Paragraph("Class    : " + s.getStandard(), normalFont));
                document.add(new Paragraph("Subjects:", normalFont));

                for (Map.Entry<String, Integer> entry : s.getMarks().entrySet()) {
                    document.add(new Paragraph("  " + entry.getKey() + " : " + entry.getValue(), normalFont));
                }

                document.add(new Paragraph("Average  : " + String.format("%.2f", s.getAverage()), normalFont));
                document.add(new Paragraph("Grade    : " + s.getGrade(), normalFont));
                document.add(new Paragraph("Remark   : " + s.getRemark(), normalFont));
                document.add(new Paragraph("\n----------------------------------------\n"));
            }

            System.out.println("PDF Report generated successfully as StudentReportCard.pdf");
        } catch (Exception e) {
            System.out.println("Error generating PDF: " + e.getMessage());
        } finally {
            document.close();
        }
    }
}
