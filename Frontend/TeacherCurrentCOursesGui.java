package Frontend;

import Core.Course;
import Core.Intronet;
import Core.Material;
import Users.Student;
import Users.Teacher;
import java.util.Scanner;

public class TeacherCurrentCOursesGui {
    public static void menu(Teacher teacher, Scanner input, boolean start) {
        int command = 0;
        int internalStage = 0;
        Student student = null;
        Course course = null;
        while (start){
            if(internalStage==0){
                System.out.println("Choose an option:");
                System.out.println("[1]Choose course");
                System.out.println("[2]Back");
                command=input.nextInt();
                if(command==1){
                    if(teacher.courses.size()>0){
                        internalStage++;
                    }
                    else {
                        System.out.println("NO CURRENT COURSES!");
                    }
                }
                else if(command==2){
                    start=false;
                }
                else {
                    System.out.println("WRONG NUMBER!");
                }
            }
            if(internalStage==1){
                SchduleDrawer.printInfoAboutTeacherCourses(teacher);
                System.out.println("Enter course number");
                int courseNum = input.nextInt();
                if(courseNum < 0 || courseNum > teacher.courses.size()){
                    System.out.println("Wrong number!");
                }
                else {
                    course = (Course) teacher.courses.stream().toArray()[courseNum-1];
                    internalStage++;
                }
            }
            if(internalStage==2){
                System.out.println("Choose an option:");
                System.out.println("[1]Manage course material");
                System.out.println("[2]View all marks");
                System.out.println("[3]Rate students");
                System.out.println("[4]Back");
                command=input.nextInt();
                if(command==1){
                    if(course.materials.size()>0){
                        internalStage++;
                    }
                    else {
                        System.out.println("NO MATERIALS!");
                    }
                }
                else if(command==2){
                    if(course.studentMarks.keySet().size()>0){
                        SchduleDrawer.printMarksForListOfStudents(course);
                    }
                    else {
                        System.out.println("NO STUDENTS!");
                    }
                }
                else if(command==3){
                    internalStage=5;
                }
                else if(command==4){
                    internalStage=0;
                }
                else {
                    System.out.println("WRONG NUMBER!");
                }
            }
            if(internalStage==3){

                System.out.println("Chose an option:");
                System.out.println("[1]Add material");
                System.out.println("[2]Remove material");
                System.out.println("[3]Back");
                command = input.nextInt();
                if(command==1){
                    System.out.println("Enter file name");
                    String filename = input.next();
                    course.materials.add((new Material(filename)));
                }
                else if(command==2){
                    internalStage++;
                }
                else if(command==3){
                    internalStage--;
                }
                else {
                    System.out.println("WRONG NUMBER!");
                }
            }
            if(internalStage==4){
                SchduleDrawer.printMaterials(course);
                System.out.println("Enter number of material:");
                int materialNum = input.nextInt();
                if(materialNum<0 || materialNum > course.materials.size()){
                    System.out.println("Wrong number!");
                }
                else {
                    course.materials.remove(materialNum-1);
                    internalStage--;
                }
            }
            if(internalStage==5){
                SchduleDrawer.printStudentsOnCourse(course);
                System.out.println("Enter student number:");
                int studentIndex = input.nextInt();
                if(studentIndex < 0 || studentIndex > course.studentMarks.keySet().size()){
                    System.out.println("WRONG NUMBER!");
                    internalStage--;
                }
                else {
                    student = (Student) Intronet.getUserById((String) course.studentMarks.keySet().toArray()[studentIndex-1]);
                    internalStage++;
                }
            }
            if(internalStage==6){
                SchduleDrawer.printMarksForCurrentStudent(student,course,0,0);
                System.out.println("Chose an option:");
                System.out.println("[1]Put mark for first attestation");
                System.out.println("[2]Put mark for first attestation");
                System.out.println("[3]Put mark for final");
                System.out.println("[4]Put abscense");
                System.out.println("[5]Back");
                command=input.nextInt();
                if(command==1){
                    System.out.println("Enter mark for first attiastation");
                    double mark = input.nextDouble();
                    if(mark<0 || mark > 30){
                        System.out.println("WRONG MARK!");
                    }
                    else {
                        student.courses.get(course.getId()).putPointForFirstAtt(mark);
                    }
                }
                else if(command==2){
                    System.out.println("Enter mark for second attiastation");
                    double mark = input.nextDouble();
                    if(mark<0 || mark > 30){
                        System.out.println("WRONG MARK!");
                    }
                    else {
                        student.courses.get(course.getId()).putPointsForFinal(mark);
                    }
                }
                else if(command==3){
                    System.out.println("Enter mark for second attiastation");
                    double mark = input.nextDouble();
                    if(mark<0 || mark > 50){
                        System.out.println("WRONG MARK!");
                    }
                    else {
                        student.courses.get(course.getId()).putPointsForFinal(mark);
                    }
                }
                else if(command==4){
                    student.courses.get(course.getId()).putAcscenseCount();
                }
                else if(command==5){
                    internalStage=2;
                }
                else{
                    System.out.println("WRONG NUMBER!");
                }
            }
        }
    }
}
