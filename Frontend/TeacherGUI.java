package Frontend;

import Core.InputVerificator;
import Users.Teacher;
import java.io.BufferedReader;
import java.io.IOException;

public class TeacherGUI {
    public static void menu(Teacher teacher,BufferedReader input) throws IOException {
        int command;
        while (teacher != null) {
            System.out.println("Choose an option:");
            System.out.println("[1]View news");
            System.out.println("[2]View email;");
            System.out.println("[3]Write a message;");
            System.out.println("[4]Print schedule;");
            System.out.println("[5]View current courses");
            System.out.println("[6]View all rating");
            System.out.println("[6]Back");
            command= InputVerificator.intValueCheck(input.readLine());
            if(command==1){
                teacher.viewAllNews();
                System.out.println("Choose an option:");
                System.out.println("[1]Make comment");
                System.out.println("[2]Back;");
                command = InputVerificator.intValueCheck(input.readLine());
                if(command==1){
                    teacher.makeComment(input);
                }
                else if (command==2) {
                    continue;
                }
                else {
                    System.out.println("Wrong number!");
                }
            }
            else if(command==2){
                teacher.viewAllMessages();
            }
            else if(command==3){
                teacher.writeMessage(input);
            }
            else if(command==4){
                SchduleDrawer.printSchedule(teacher.getSchedule());
            }
            else if(command==5){
                TeacherCurrentCOursesGui.menu(teacher,input);
            }
            else if(command==6){
                SchduleDrawer.printTeacherRatingForAllCourses(teacher);
//                if(teacher.courses.size()>0){
//                    SchduleDrawer.printTeacherRatingForAllCourses(teacher);
//                }
//                else {
//                    System.out.println("NO COURSES!");
//                }
            }
            else if(command==7){
                teacher=null;
            }
            else {
                System.out.println("WRONG NUMBER!");
            }
        }
    }
}
