package Frontend;

import Core.*;
import Users.Student;
import Users.Teacher;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;


public class TeacherCurrentCoursesGui {
    public static void menu(Teacher teacher,BufferedReader input) throws IOException {
        int command;
        int internalStage = 0;
        Student student = null;
        Course course = null;
        boolean start = true;
        if(teacher.courses.size()<1){
            start=false;
            System.out.println("No courses!");
        }
        while (start){
            if(internalStage==0){
                SchduleDrawer.printInfoAboutTeacherCourses(teacher);
                System.out.println("Choose an option:");
                System.out.println("[1]Choose course");
                System.out.println("[2]Back");
                command= InputVerification.intValueCheck(input.readLine());
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
                System.out.println("Enter course number");
                int courseNum = InputVerification.intValueCheck(input.readLine());
                course = teacher.chooseCourse(courseNum);
                if(course!=null)
                {
                    internalStage++;
                }
                else {
                    internalStage--;
                }
            }
            if(internalStage==2){
                System.out.println("Choose an option:");
                System.out.println("[1]Manage course material");
                System.out.println("[2]View all marks");
                System.out.println("[3]Rate students");
                System.out.println("[4]Finish course");
                System.out.println("[5]Back");
                command= InputVerification.intValueCheck(input.readLine());
                if(command==1){
                    internalStage=7;
                }
                else if(command==2){
                    SchduleDrawer.printMarksForListOfStudents(course);
                }
                else if(command==3){
                    internalStage=5;
                } else if (command==4)
                {
                    internalStage=3;
                }
                else if(command==5){
                    internalStage=0;
                }
                else {
                    System.out.println("WRONG NUMBER!");
                }
            }
            if(internalStage==3){
                for (String id : course.studentMarks.keySet()){
                    Student passedStudent = (Student) Intranet.getInstance().getUserById(id);
                    Mark mark = course.studentMarks.get(id);
                    if(!mark.finalHeld){
                        System.out.println(passedStudent + " has no points for final!");
                    }
                    else {
                        if(mark.isRetake()){
                            Logs.AddToLog("Student: " + Intranet.getInstance().getUserById(id).toString() + " fail" + course.name);
                        }
                        else {
                            Logs.AddToLog("Student: " + Intranet.getInstance().getUserById(id).toString() + " pass" + course.name);
                            passedStudent.passedCoursesCnt++;
                            passedStudent.transcript.computeIfAbsent(passedStudent.yearOfStudy, k -> new HashMap<>());
                            HashMap<String,Mark> passedCourseAndMarks = passedStudent.transcript.get(passedStudent.yearOfStudy);
                            passedCourseAndMarks.put(course.getId(),course.studentMarks.get(passedStudent.getId()));
                            passedStudent.nextCourse();
                        }
                    }
                    Intranet.dropStudentFromCourse(passedStudent,course);
                }
                internalStage=2;
            }
            if(internalStage==4){
                SchduleDrawer.printMaterials(course);
                if(course.materials.size()>0){
                    System.out.println("Enter number of material:");
                    int materialNum = InputVerification.intValueCheck(input.readLine());
                    try {
                        course.materials.remove(materialNum-1);
                    }
                    catch (ArrayIndexOutOfBoundsException e){
                        System.out.println("Wrong number!");
                    }
                    Logs.AddToLog(teacher + " remove material from" + course.name);
                }
                internalStage=2;
            }

            if(internalStage==5){
                SchduleDrawer.printStudentsOnCourse(course);
                System.out.println(course.studentMarks.size());
                if(course.studentMarks.size()>0){
                    System.out.println("Enter student number:");
                    int studentIndex = InputVerification.intValueCheck(input.readLine());
                    try {
                        student = (Student) Intranet.getInstance().getUserById((String) course.studentMarks.keySet().toArray()[studentIndex-1]);
                        internalStage++;
                    }
                    catch (ArrayIndexOutOfBoundsException e){
                        System.out.println("WRONG NUMBER!");
                        internalStage=2;
                    }
                }
                else {
                    internalStage=2;
                }
            }
            if(internalStage==6){
                SchduleDrawer.printMarksForCurrentStudent(student,course,0,0,(student.toString()).length());
                System.out.println("Chose an option:");
                System.out.println("[1]Put mark for first attestation");
                System.out.println("[2]Put mark for first attestation");
                System.out.println("[3]Put mark for final");
                System.out.println("[4]Put absence");
                System.out.println("[5]Back");
                command= InputVerification.intValueCheck(input.readLine());
                if(command==1){
                    System.out.println("Enter mark for first attestation");
                    double mark = InputVerification.doubleValueCheck(input.readLine());
                    if(mark<0 || mark > 60){
                        System.out.println("WRONG MARK!");
                    }
                    else {
                        student.courses.get(course.getId()).putPointForFirstAtt(mark);
                        course.studentMarks.get(student.getId()).putPointForFirstAtt(mark);
                        Logs.AddToLog(teacher + " put" + mark + " for first att on" + course.name);
                    }
                }
                else if(command==2){
                    System.out.println("Enter mark for second attestation");
                    double mark = InputVerification.doubleValueCheck(input.readLine());
                    if(mark<0 || mark > 60){
                        System.out.println("WRONG MARK!");
                    }
                    else {
                        student.courses.get(course.getId()).putPointForSecondAtt(mark);
                        course.studentMarks.get(student.getId()).putPointForSecondAtt(mark);
                        Logs.AddToLog(teacher + " put" + mark + " for second att on" + course.name);
                    }
                }
                else if(command==3){
                    System.out.println("Enter mark for final");
                    double mark = InputVerification.doubleValueCheck(input.readLine());
                    if(mark<0 || mark > 100){
                        System.out.println("WRONG MARK!");
                    }
                    else {
                        student.courses.get(course.getId()).putPointsForFinal(mark);
                        course.studentMarks.get(student.getId()).putPointsForFinal(mark);
                        Logs.AddToLog(teacher + "put" + mark + " for final on" + course.name );
                    }
                }
                else if(command==4){
                    Logs.AddToLog(teacher + "put absence");
                    student.courses.get(course.getId()).putAcscenseCount();
                    course.studentMarks.get(student.getId()).putAcscenseCount();
                }
                else if(command==5){
                    internalStage=2;
                }
                else{
                    System.out.println("WRONG NUMBER!");
                }
            }
            if(internalStage==7) {
                System.out.println("Chose an option:");
                System.out.println("[1]Add material");
                System.out.println("[2]Remove material");
                System.out.println("[3]Back");
                command = InputVerification.intValueCheck(input.readLine());
                if (command == 1) {
                    System.out.println("Enter file name");
                    String filename = input.readLine();
                    course.materials.add((new Material(filename)));
                    Logs.AddToLog(teacher + " add material to" + course.name);
                } else if (command == 2) {
                    internalStage = 4;
                } else if (command == 3) {
                    internalStage = 2;
                } else {
                    System.out.println("WRONG NUMBER!");
                }
            }
        }
    }
}
