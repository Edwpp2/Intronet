package Frontend;

import Users.Teacher;
import java.util.Scanner;
public class TeacherGUI {
    public static void menu(Teacher teacher) {
        Scanner input = new Scanner(System.in);
        int command = 0;
        while (teacher != null) {
            System.out.println("Choose an option:");
            System.out.println("[1]View news");
            System.out.println("[2]View email;");
            System.out.println("[3]Write a message;");
            System.out.println("[4]Print schedule;");
            System.out.println("[5]View current courses");
            System.out.println("[6]View all rating");
            System.out.println("[6]Back");
            command=input.nextInt();
            if(command==1){
                teacher.viewAllNews();
            }
            else if(command==2){
                teacher.viewAllMessages();
            }
            else if(command==3){
                System.out.println("Enter a user login:");
                String login = input.next();
                System.out.println("Enter a text:");
                String text = input.next();
                teacher.writeMessage(login,text);
            }
            else if(command==4){
                SchduleDrawer.printSchedule(teacher.getSchedule());
            }
            else if(command==5){
                if(teacher.courses.size()>0){
                    TeacherCurrentCOursesGui.menu(teacher,input,true);
                }
                else {
                    System.out.println("NO COURSES!");
                }
            }
            else if(command==6){
                if(teacher.courses.size()>0){
                    SchduleDrawer.printTeacherRatingForAllCourses(teacher);
                }
                else {
                    System.out.println("NO COURSES!");
                }
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

