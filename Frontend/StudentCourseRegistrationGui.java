package Frontend;

import Core.Course;
import Core.CourseAdepter;
import Core.Intronet;
import Core.Request;
import Enums.RequestType;
import Users.Student;

import java.util.Scanner;

public class StudentCourseRegistrationGui {
    public static void menu(Student student, Scanner input, boolean start) {
        int command = 0;
        int internalStage = 0;
        Course course = null;
        while (start){
            if(internalStage==0){
                SchduleDrawer.printCoursesForRegistration(student);
                System.out.println("Chose an option:");
                System.out.println("[1]Make a request for registration on course under number #;");
                System.out.println("[2]Back;");
                command = input.nextInt();
                if(command==1){
                    internalStage++;
                }
                else if (command==2){
                    start=false;
                }
                else {
                    System.out.println("WRONG NUMBER!");
                }
            }
            if(internalStage==1){
                System.out.println("Enter number of the course");
                int courseNum = input.nextInt();
                if(courseNum > 0 && courseNum < Intronet.courses.size()){
                    course = (Course) CourseAdepter.getObjectFromArray(Intronet.courses.toArray(),courseNum);
                    internalStage++;
                }
                else {
                    System.out.println("Wrong number!");
                }
            }
            if(internalStage==2){
                System.out.println("Chose an option:");
                System.out.println("[1]Make request to add");
                System.out.println("[2]Back");
                command = input.nextInt();
                if(command==1){
                    Intronet.requests.add(new Request(course.getId(),student.getId(), RequestType.ADDCOURSE,student.getFaculty()));
                }
                else if(command==2){
                    internalStage=0;
                }
                else {
                    System.out.println("WRONG NUMBER!");
                }

            }
        }
    }
}

