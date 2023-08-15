package Frontend;
import Core.*;
import Core.News;
import Users.Manager;
import Users.User;
import java.io.BufferedReader;
import java.io.IOException;


public class ManagerGUI {
    public static void menu(Manager manager,BufferedReader input) throws IOException {
        int command = 0;
        News news = null;
        User user = null;
        while (manager != null) {
            System.out.println("Chose an option:");
            System.out.println("[1]View news");
            System.out.println("[2]View email;");
            System.out.println("[3]Write a message;");
            System.out.println("[4]Manage news;");
            System.out.println("[5]Manage requests");
            System.out.println("[6]Manage courses");
            System.out.println("[7]Manage users");
            System.out.println("[8]Exit.");
            command = InputVerification.intValueCheck(input.readLine());
            if(command==1){
                manager.viewAllNews();
                manager.viewAllNews();
                System.out.println("Choose an option:");
                System.out.println("[1]Make comment");
                System.out.println("[2]Back;");
                command = InputVerification.intValueCheck(input.readLine());
                if(command==1){
                    manager.makeComment(input);
                }
                else if (command==2) {
                    continue;
                }
                else {
                    System.out.println("Wrong number!");
                }
            }
            else if(command==2){
                manager.viewAllMessages();
            }
            else if(command==3){
                manager.writeMessage(input);
            }
            else if(command==4){
                ManagerNewsGUI.menu(manager,news,input);
            }
            else if(command==5){
                ManagerRequestsGUI.menu(manager,input);
            }
            else if(command==6){
                ManagerCourseGUI.menu(input);
            }
            else if(command==7){
                ManagerUserManagmentGUI.menu(user,input);
            }
            else if(command==8){
                manager=null;
            }
            else {
                System.out.println("WRONG NUMBER!");
            }
        }
    }
}
