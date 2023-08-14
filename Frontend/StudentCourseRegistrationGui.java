package Frontend;

import Core.*;
import Enums.RequestType;
import Users.Student;
import java.io.BufferedReader;
import java.io.IOException;


public class StudentCourseRegistrationGui {
    public static void menu(Student student,BufferedReader input) throws IOException {
        int command;
        int internalStage = 0;
        Course course = null;
        boolean start = true;
        if(Intronet.getInstance().courses.size()<1){
            start=false;
            System.out.println("NO COURSES FOR REGISTRATION!");
        }
        while (start){
            if(internalStage==0){
                SchduleDrawer.printCoursesForRegistration(student);
                System.out.println("Chose an option:");
                System.out.println("[1]Make a request for registration on course under number #;");
                System.out.println("[2]Back;");
                command = InputVerificator.intValueCheck(input.readLine());
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
                int courseNum = InputVerificator.intValueCheck(input.readLine());
                try{
                    course = (Course) (Intronet.getInstance().courses.toArray()[courseNum-1]);
                    internalStage++;
                }
                catch (NullPointerException e){
                    System.out.println("WRONG NUMBER!");
                }

            }
            if(internalStage==2){
                System.out.println("Chose an option:");
                System.out.println("[1]Make request to add");
                System.out.println("[2]Back");
                command = InputVerificator.intValueCheck(input.readLine());
                if(command==1){
                    Intronet.getInstance().requests.add(new Request(course.getId(),student.getId(), RequestType.ADDCOURSE,student.getFaculty()));
                    System.out.println("REQUEST WAS ADDED!");
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

