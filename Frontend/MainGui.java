package Frontend;

import Core.Intronet;
import Enums.Role;
import Users.Manager;
import Users.Student;
import Users.Teacher;
import Users.User;

import java.util.Scanner;

public class MainGui {

    public static void menu(){
        Scanner input = new Scanner(System.in);
        int internalStage = 0;
        int command = 0;
        User user = null;
        while(true){
            if(internalStage==0){
                System.out.println("WELCOME TO INTRONET!");
                System.out.println("Choose an option");
                System.out.println("[1]Login");
                System.out.println("[2]Exit");
                command = input.nextInt();
                if(command==1){
                    System.out.println("Enter login");
                    String login = input.next();
                    System.out.println("Enter password");
                    String password = input.next();
                    user = Intronet.login(login,password);
                    if(user==null){
                        System.out.println("WRONG LOGIN OR PASSWORD!");
                    }
                    else {
                        internalStage++;
                    }
                }
                else if(command==2){
                    Intronet.serializeIntronet("intronet.txt");
                    System.exit(0);
                }
                else {
                    System.out.println("WRONG NUMBER!");
                }
            }
            if(internalStage==1){
                if(user.role == Role.TEACHER){
                    TeacherGUI.menu((Teacher) user);
                }
                else if(user.role == Role.MANAGER){
                    ManagerGUI.menu((Manager)user);
                }
                else if(user.role==Role.STUDENT){
                    StudentGUI.menu((Student) user);
                }
                internalStage--;
            }
        }


    }
}
