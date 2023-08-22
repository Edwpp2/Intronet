package Frontend;

import Core.Course;
import Core.InputVerification;
import Core.Intranet;
import Core.Request;
import Enums.RequestType;
import Users.Student;
import java.io.BufferedReader;
import java.io.IOException;

public class StudentCurrentCoursesManagment {
    public static void menu(Student student,BufferedReader input) throws IOException {
        int command;
        int internalStage = 0;
        Course course = null;
        boolean start = true;
        if(student.courses.size()<1){
            start=false;
            System.out.println("NO CURRENT COURSES!");
        }
        while (start){
            if(internalStage==0){
                SchduleDrawer.printInfoAboutStudentCourses(student);
                System.out.println("Choose an option:");
                System.out.println("[1]Choose course");
                System.out.println("[2]Back");
                command = InputVerification.intValueCheck(input.readLine());
                if(command==1){
                    System.out.println("Enter course number!");
                    int courseNum = InputVerification.intValueCheck(input.readLine());
                    if(courseNum>0 && courseNum <= student.courses.size()){
                        course = Intranet.getInstance().getCourseById((String) student.courses.keySet().toArray()[courseNum-1]);
                        internalStage++;
                    }
                    else {
                        System.out.println("WRONG NUMBER!");
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
                System.out.println("Chose an option:");
                System.out.println("[1]View info about course");
                System.out.println("[2]View marks");
                System.out.println("[3]Rate teacher");
                System.out.println("[4]Drop course");
                System.out.println("[5]Back");
                command = InputVerification.intValueCheck(input.readLine());
                if(command==1){
                    SchduleDrawer.printInfoAboutCourse(course,course.name.length(),course.code.length(),0,0,false);
                    SchduleDrawer.printMaterials(course);
                }
                else if(command==2){
                    SchduleDrawer.printMarksForCurrentStudent(student,course,0,1,(student.toString()).length());
                }
                else if(command==3){
                    if(course.teacher==null){
                        System.out.println("There are no teacher on course");
                    }
                    else {
                        System.out.println("Enter integer value btween 1 and 5");
                        int rating = InputVerification.intValueCheck(input.readLine());
                        student.rateTeacher(course,rating);
                    }
                }
                else if(command==4){
                    System.out.println("Chose an option:");
                    System.out.println("[1]Make request to drop");
                    System.out.println("[2]Back");
                    command = InputVerification.intValueCheck(input.readLine());
                    if(command==1){
                        Intranet.getInstance().requests.add(new Request(course.getId(),student.getId(), RequestType.DROPCOURSE,student.getFaculty()));
                    }
                    else if(command==2){
                        continue;
                    }
                    else {
                        System.out.println("WRONG NUMBER!");
                    }
                }
                else if(command==5){
                    internalStage--;
                    course=null;
                }
                else {
                    System.out.println("WRONG NUMBER!");
                }
            }
        }
    }
}
