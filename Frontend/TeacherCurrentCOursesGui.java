package Frontend;

import Core.*;
import Users.Student;
import Users.Teacher;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;


public class TeacherCurrentCOursesGui {
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
                SchduleDrawer.printInfoAboutTeacherCourses(teacher);
                System.out.println("Enter course number");
                int courseNum = InputVerification.intValueCheck(input.readLine());
                try {
                    course = Intranet.getInstance().getCourseById((String) teacher.courses.toArray()[courseNum-1]);
                    internalStage++;
                }
                catch (ArrayIndexOutOfBoundsException e){
                    System.out.println("Wrong number!");
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
                    System.out.println("Chose an option:");
                    System.out.println("[1]Add material");
                    System.out.println("[2]Remove material");
                    System.out.println("[3]Back");
                    command = InputVerification.intValueCheck(input.readLine());
                    if(command==1){
                        System.out.println("Enter file name");
                        String filename = input.readLine();
                        course.materials.add((new Material(filename)));
                        Logs.AddToLog(teacher.name + teacher.surname + "add material to" + course.name);
                    }
                    else if(command==2){
                        internalStage=4;
                    }
                    else if(command==3){
                        continue;
                    }
                    else {
                        System.out.println("WRONG NUMBER!");
                    }
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
                    if(!course.studentMarks.get(id).finalHeld){
                        System.out.println("Some students has no points for final!");
                        continue;
                    }
                }
                for (String id : course.studentMarks.keySet()){
                    Student student1 = (Student) Intranet.getInstance().getUserById(id);
                    if(course.studentMarks.get(id).isRetake()){
                        Logs.AddToLog("Student: " + Intranet.getInstance().getUserById(id).name + " " + Intranet.getInstance().getUserById(id).surname + " fail" + course.name);
                    }
                    else {
                        Logs.AddToLog("Student: " + Intranet.getInstance().getUserById(id).name + " " + Intranet.getInstance().getUserById(id).surname + " pass" + course.name);
                        student1.passedCoursesCnt++;
                        student1.transcript.computeIfAbsent(student1.yearOfStudy, k -> new HashMap<>());
                        HashMap<String,Mark> passedCourseAndMarks = student1.transcript.get(student1.yearOfStudy);
                        passedCourseAndMarks.put(course.getId(),course.studentMarks.get(student1.getId()));
                        student1.nextCourse();
                    }
                    Intranet.dropStudentFromCourse(student1,course);
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
                    Logs.AddToLog(teacher.name + teacher.surname + "remove material from" + course.name);
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
                        System.out.println(student.name);
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
                SchduleDrawer.printMarksForCurrentStudent(student,course,0,0);
                System.out.println("Chose an option:");
                System.out.println("[1]Put mark for first attestation");
                System.out.println("[2]Put mark for first attestation");
                System.out.println("[3]Put mark for final");
                System.out.println("[4]Put abscense");
                System.out.println("[5]Back");
                command= InputVerification.intValueCheck(input.readLine());
                if(command==1){
                    System.out.println("Enter mark for first attiastation");
                    double mark = InputVerification.doubleValueCheck(input.readLine());
                    if(mark<0 || mark > 60){
                        System.out.println("WRONG MARK!");
                    }
                    else {
                        student.courses.get(course.getId()).putPointForFirstAtt(mark);
                        course.studentMarks.get(student.getId()).putPointForFirstAtt(mark);
                        Logs.AddToLog(teacher.name + teacher.surname + "put" + mark + " for first att on" + course.name);
                    }
                }
                else if(command==2){
                    System.out.println("Enter mark for second attiastation");
                    double mark = InputVerification.doubleValueCheck(input.readLine());
                    if(mark<0 || mark > 60){
                        System.out.println("WRONG MARK!");
                    }
                    else {
                        student.courses.get(course.getId()).putPointForSecondAtt(mark);
                        course.studentMarks.get(student.getId()).putPointForSecondAtt(mark);
                        Logs.AddToLog(teacher.name + teacher.surname + "put" + mark + " for second att on" + course.name);
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
                        Logs.AddToLog(teacher.name + teacher.surname + "put" + mark + " for final on" + course.name );
                    }
                }
                else if(command==4){
                    Logs.AddToLog(teacher.name + teacher.surname + "put absence");
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
        }
    }
}
