package Frontend;
import Core.*;
import Core.Intronet;
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
            command = InputVerificator.intValueCheck(input.readLine());
            if(command==1){
                if(Intronet.getInstance().news.size()>0){
                    Intronet.getInstance().printNews();
                }
                else {
                    System.out.println("No news!");
                }
            }
            else if(command==2){
                if(manager.messages.size()>0){
                    manager.viewAllMessages();
                }
                else {
                    System.out.println("NO MESSAGES!");
                }
            }
            else if(command==3){
                System.out.println("Enter login of user!");
                String login = input.readLine();
                user = Intronet.getInstance().getUserByLogin(login);
                if (user == null) {
                    System.out.println("User not found!");
                } else {
                    System.out.println("Write a text!");
                    String text = input.readLine();
                    Message message = new Message(login, text);
                    user.messages.add(message);
                }
            }
            else if(command==4){
                if(Intronet.getInstance().news.size()>0){
                    ManagerNewsGUI.menu(news,input);
                }
                else {
                    System.out.println("NO NEWS!");
                }
            }
            else if(command==5){
                System.out.println(Intronet.getInstance().getFacultyRequest(manager).length);
                System.out.println(Intronet.getInstance().requests.size());
                if(Intronet.getInstance().getFacultyRequest(manager).length>0){
                    ManagerRequestsGUI.menu(manager,input);
                }
                else {
                    System.out.println("NO REQUESTS!");
                }
            }
            else if(command==6){
                if(Intronet.getInstance().courses.size()>0){
                    ManagerCourseGUI.menu(input);
                }
                else {
                    System.out.println("NO COURSES!");
                }
            }
            else if(command==7){
                if(Intronet.getInstance().users.size()>1){
                    ManagerUserManagmentGUI.menu(user,input);
                }
                else {
                    System.out.println("NO USERS TO MANAGE!");
                }
            }
            else if(command==8){
                manager=null;
            }
            else if (command==0){
                continue;
            } else {
                System.out.println("WRONG NUMBER!");
            }
        }
    }
}
