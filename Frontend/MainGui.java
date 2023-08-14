package Frontend;

import Core.InputVerificator;
import Core.Intronet;
import Enums.Role;
import Users.Manager;
import Users.Student;
import Users.Teacher;
import Users.User;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class MainGui {
    public static void menu() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int command;
        User user;
        while(true){
            System.out.println("WELCOME TO INTRONET!");
            System.out.println("Choose an option");
            System.out.println("[1]Login");
            System.out.println("[2]Exit");
            command = InputVerificator.intValueCheck(input.readLine());
            if(command==1){
                user = Intronet.getInstance().login(input);
                if(user==null){
                    System.out.println("WRONG LOGIN OR PASSWORD!");
                }
                else {
                    if(user.role == Role.TEACHER){
                        TeacherGUI.menu((Teacher) user,input);
                    }
                    else if(user.role == Role.MANAGER){
                        ManagerGUI.menu((Manager)user,input);
                    }
                    else if(user.role==Role.STUDENT){
                        StudentGUI.menu((Student) user,input);
                    }
                }
            }
            else if(command==2){
                Intronet.serializeIntronet("intronet.txt");
                input.close();
                System.exit(0);
            }
            else {
                System.out.println("WRONG NUMBER!");
            }
        }
    }
}
