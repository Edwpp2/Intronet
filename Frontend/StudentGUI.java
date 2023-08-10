package Frontend;

import Core.Intronet;
import Core.Message;
import Users.Student;
import Users.User;
import java.util.Scanner;

public class StudentGUI {
    public static void menu(Student student) {
        Scanner input = new Scanner(System.in);
        int command = 0;
        while (student != null) {
            System.out.println("Choose an option:");
            System.out.println("[1]View news");
            System.out.println("[2]View email;");
            System.out.println("[3]Write a message;");
            System.out.println("[4]View schedule;");
            System.out.println("[5]View list of courses for registration;");
            System.out.println("[6]View list of current courses;");
            System.out.println("[7]View transcript");
            System.out.println("[8]Exit.");
            command = input.nextInt();
            System.out.println(command);
            if(command==1){
                student.viewAllNews();
            }
            else if(command==2){
                student.viewAllMessages();
            }
            else if(command==3){
                System.out.println("Enter login of user!");
                String login = input.next();
                User user = Intronet.getUserByLogin(login);
                if (user == null) {
                    System.out.println("User not found!");
                } else {
                    System.out.println("Write a text!");
                    String text = input.next();
                    Message message = new Message(login, text);
                    user.messages.add(message);
                }
            }
            else if (command == 4) {
                SchduleDrawer.printSchedule(student.getSchedule());
            }
            else if (command == 5) {
                if(Intronet.courses.size()>0){
                    StudentCourseRegistrationGui.menu(student, input, true);
                }
                else {
                    System.out.println("NO COURSES FO REGISTRATION!");
                }
            }
            else if (command == 6) {
                if(student.courses.size()>0){
                    StudentCurrentCoursesManagment.menu(student, input, true);
                }
                else {
                    System.out.println("NO CURRENT COURSES!");
                }
            }
            else if (command == 7) {
                student = null;
            }
            else if (command == 8) {
                student = null;
            }
            else {
                System.out.println("WRONG NUMBER!");
            }
        }
    }
}

