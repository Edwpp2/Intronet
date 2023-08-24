package Frontend;

import Core.*;
import Users.Student;
import Users.Teacher;
import java.io.BufferedReader;
import java.io.IOException;

public class TeacherCurrentCoursesGui {
    private static final int STAGE_CHOOSE_OPTION = 1;
    private static final int STAGE_CHOOSE_COURSE = 2;
    private static final int STAGE_MANAGE_COURSE = 3;
    private static final int STAGE_REMOVE_COURSE_MATERIAL = 4;
    private static final int STAGE_RATE_STUDENT = 5;
    private static final int STAGE_CHOOSE_STUDENT = 6;
    private static final int STAGE_MANAGE_COURSE_MATERIAL = 7;
    public static void menu(Teacher teacher,BufferedReader input) throws IOException {

        int command;
        int internalStage=1;
        Student student = null;
        Course course = null;
        boolean start = true;
        if(teacher.courses.size()<1){
            start=false;
            System.out.println("No courses!");
        }
        while (start){
            if(internalStage==STAGE_CHOOSE_OPTION){
                SchduleDrawer.printInfoAboutTeacherCourses(teacher);
                System.out.println("Choose an option:");
                System.out.println("[1]Choose course");
                System.out.println("[2]Back");
                command= InputVerification.intValueCheck(input.readLine());
                if(command==1){
                    if(teacher.courses.size()>0){
                        internalStage=STAGE_CHOOSE_COURSE;
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
            if(internalStage==STAGE_CHOOSE_COURSE){
                System.out.println("Enter course number");
                int courseNum = InputVerification.intValueCheck(input.readLine());
                course = teacher.chooseCourse(courseNum);
                if(course!=null)
                {
                    internalStage=STAGE_MANAGE_COURSE;
                }
                else {
                    internalStage=STAGE_CHOOSE_OPTION;
                }
            }
            if(internalStage==STAGE_MANAGE_COURSE) {
                System.out.println("Choose an option:");
                System.out.println("[1]Manage course material");
                System.out.println("[2]View all marks");
                System.out.println("[3]Rate students");
                System.out.println("[4]Finish course");
                System.out.println("[5]Back");
                command = InputVerification.intValueCheck(input.readLine());
                if (command == 1) {
                    internalStage = STAGE_MANAGE_COURSE_MATERIAL;
                } else if (command == 2) {
                    SchduleDrawer.printMarksForListOfStudents(course);
                } else if (command == 3) {
                    internalStage = STAGE_CHOOSE_STUDENT;
                } else if (command == 4){
                    teacher.finishCourse(course);
                }
                else if(command==5){
                    internalStage=STAGE_CHOOSE_OPTION;
                }
                else {
                    System.out.println("WRONG NUMBER!");
                }
            }
            if(internalStage==STAGE_REMOVE_COURSE_MATERIAL){
                SchduleDrawer.printMaterials(course);
                if(course.materials.size()>0){
                    System.out.println("Enter number of material:");
                    int materialNum = InputVerification.intValueCheck(input.readLine());
                    teacher.removeMaterial(course,materialNum);
                }
                internalStage=STAGE_MANAGE_COURSE;
            }

            if(internalStage==STAGE_CHOOSE_STUDENT){
                SchduleDrawer.printStudentsOnCourse(course);
                if(course.studentMarks.size()>0){
                    System.out.println("Enter student number:");
                    int studentIndex = InputVerification.intValueCheck(input.readLine());
                    student = teacher.chooseStudent(course,studentIndex);
                    if(student!=null){
                        internalStage=STAGE_RATE_STUDENT;
                    }
                }
                else {
                    internalStage=STAGE_MANAGE_COURSE;
                }
            }
            if(internalStage==STAGE_RATE_STUDENT){
                SchduleDrawer.printMarksForCurrentStudent(student,course,0,0,(student.toString()).length());
                System.out.println("Chose an option:");
                System.out.println("[1]Put mark for first attestation");
                System.out.println("[2]Put mark for first attestation");
                System.out.println("[3]Put mark for final");
                System.out.println("[4]Put absence");
                System.out.println("[5]Back");
                command = InputVerification.intValueCheck(input.readLine());
                if (command == 1) {
                    System.out.println("Enter mark for first attestation");
                    double mark = InputVerification.doubleValueCheck(input.readLine());
                    teacher.putMarkForFirstAtt(mark,student,course);
                } else if (command == 2) {
                    System.out.println("Enter mark for second attestation");
                    double mark = InputVerification.doubleValueCheck(input.readLine());
                    teacher.putMarkForSecondAtt(mark,student,course);
                } else if (command == 3) {
                    System.out.println("Enter mark for final");
                    double mark = InputVerification.doubleValueCheck(input.readLine());
                    teacher.putMarkForFinal(mark,student,course);
                } else if (command == 4) {
                    teacher.putAbsence(student,course);
                } else if (command == 5) {
                    internalStage = STAGE_MANAGE_COURSE;
                } else {
                    System.out.println("WRONG NUMBER!");
                }
            }
            if(internalStage==STAGE_MANAGE_COURSE_MATERIAL) {
                System.out.println("Chose an option:");
                System.out.println("[1]Add material");
                System.out.println("[2]Remove material");
                System.out.println("[3]Back");
                command = InputVerification.intValueCheck(input.readLine());
                if (command == 1) {
                    System.out.println("Enter file name");
                    String filename = input.readLine();
                    teacher.addMaterial(course,filename);
                } else if (command == 2) {
                    internalStage = STAGE_REMOVE_COURSE_MATERIAL;
                } else if (command == 3) {
                    internalStage = STAGE_MANAGE_COURSE;
                } else {
                    System.out.println("WRONG NUMBER!");
                }
            }
        }
    }
}
