package comreport;

import java.util.*;

public class Student {
    private String name;
    private int roll;
    private String standard;
    private Map<String, Integer> marks;

    public Student(String name, int roll, String standard, Map<String, Integer> marks) {
        this.name = name;
        this.roll = roll;
        this.standard = standard;
        this.marks = marks;
    }

    public String getName() {
        return name;
    }

    public int getRoll() {
        return roll;
    }

    public String getStandard() {
        return standard;
    }

    public Map<String, Integer> getMarks() {
        return marks;
    }

    public double getAverage() {
        if (marks.isEmpty()) return 0;
        int total = 0;
        for (int mark : marks.values()) {
            total += mark;
        }
        return (double) total / marks.size();
    }

    public String getGrade() {
        double avg = getAverage();
        if (avg >= 90) return "A+";
        else if (avg >= 80) return "A";
        else if (avg >= 70) return "B+";
        else if (avg >= 60) return "B";
        else if (avg >= 50) return "C";
        else return "F";
    }

    public String getRemark() {
        String grade = getGrade();
        return switch (grade) {
            case "A+" -> "Excellent";
            case "A" -> "Very Good";
            case "B+" -> "Good";
            case "B" -> "Average";
            case "C" -> "Needs Improvement";
            default -> "Fail";
        };
    }
}
