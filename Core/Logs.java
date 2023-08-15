package Core;

import Users.Student;
import Users.User;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class Logs {
    public static void AddToLog(String text, User userSourse){
        String fileName = "logs.txt";
        try {
            LocalDateTime currentDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
            String formattedDateTime = currentDateTime.format(formatter);

            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(formattedDateTime + ":" + " " + userSourse.name + " " + userSourse.surname);
            bufferedWriter.newLine();
            bufferedWriter.write(text);
            bufferedWriter.newLine();

            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void AddToLog(String text){
        String fileName = "logs.txt";
        try {
            LocalDateTime currentDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
            String formattedDateTime = currentDateTime.format(formatter);

            FileWriter fileWriter = new FileWriter(fileName,true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(formattedDateTime + ":" + " " + "SYSTEM");
            bufferedWriter.newLine();
            bufferedWriter.write(text);
            bufferedWriter.newLine();

            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String toCell(String content,int columnLenght)
    {
        String cell ="";
        int length = Math.max(columnLenght,content.length());
        String leftAlignFormat = "| " + "%-" + (length) + "s |";
        for (int i = 0; i < 5; i++) {
            cell = String.format(leftAlignFormat, content, i * i);
        }
        return cell;
    }
    public static void print(int i, String content, String delim, String topHeader, int limit, BufferedWriter bufferedWriter) {
        try {
            if (i == 0) {
                bufferedWriter.write(delim);
                bufferedWriter.newLine();
                bufferedWriter.write(topHeader);
                bufferedWriter.newLine();
                bufferedWriter.write(delim);
                bufferedWriter.newLine();
            }
            bufferedWriter.write(content);
            bufferedWriter.newLine();
            bufferedWriter.write(delim);
            bufferedWriter.newLine();
            if (i == limit) {
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveTranscript(Student student) {
        String code = toCell("Code", 9);
        String credits = toCell("Credits", 9);
        String points = toCell("Points", 9);
        String letterPoints = toCell("Letter points", "Letter points".length());
        String gpa = toCell("GPA", 9);

        String fileName = student.name + "_" + student.surname + "_" +"Transcript.txt";
        try {
            FileWriter fileWriter1 = new FileWriter(fileName,true);
            BufferedWriter bufferedWriter1 = new BufferedWriter(fileWriter1);

            for (int yearOfStudy : student.transcript.keySet()) {
                HashMap<String, Mark> courseAndMark = student.transcript.get(yearOfStudy);
                int i = 0;
                String courseName = toCell("Course name", student.maxTranscriptCourseName(yearOfStudy));
                String header = code + courseName + credits + points + letterPoints + gpa;
                String delim = "+" + "-".repeat(header.length() - 2) + "+";

                for (String courseId : courseAndMark.keySet()) {
                    Course course = Intranet.getInstance().getCourseById(courseId);
                    courseName = toCell(course.name, student.maxCourseName());
                    credits = toCell(course.credits + "", 9);
                    points = toCell(courseAndMark.get(course.getId()).getFinalPoint() + "", 9);
                    code = toCell(course.code,9);
                    letterPoints = toCell(courseAndMark.get(course.getId()).getLatterMarks(courseAndMark.get(course.getId()).getFinalPoint()) + "", "Letter points".length());
                    gpa = toCell(courseAndMark.get(course.getId()).getFinalPoint() + "", 9);
                    String content = code + courseName + credits + points + letterPoints + gpa;
                    print(i, content, delim, header, courseAndMark.size(), bufferedWriter1);
                    i++;
                }
            }

            bufferedWriter1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
