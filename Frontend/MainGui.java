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
        int internalStage = 0;
        int command;
        User user = null;
        while(true){
            if(internalStage==0){
                System.out.println("WELCOME TO INTRONET!");
                System.out.println("Choose an option");
                System.out.println("[1]Login");
                System.out.println("[2]Exit");
                command = InputVerificator.intValueCheck(input.readLine());
                if(command==1){
                    System.out.println("Enter login");
                    String login = input.readLine();
                    System.out.println("Enter password");
                    String password = input.readLine();
                    user = Intronet.getInstance().login(login,password);
                    if(user==null){
                        System.out.println("WRONG LOGIN OR PASSWORD!");
                    }
                    else {
                        internalStage++;
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
            if(internalStage==1){
                if(user.role == Role.TEACHER){
                    TeacherGUI.menu((Teacher) user,input);
                }
                else if(user.role == Role.MANAGER){
                    ManagerGUI.menu((Manager)user,input);
                }
                else if(user.role==Role.STUDENT){
                    StudentGUI.menu((Student) user,input);
                }
                internalStage--;
            }
        }
    }
}
