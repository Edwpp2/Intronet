package Frontend;

import Core.InputVerification;
import Core.Intranet;
import Users.Admin;
import Users.User;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Vector;

public class AdminGUI {
    public static void menu(Admin admin, BufferedReader input) throws IOException {
        Vector<User> users = Intranet.getInstance().users;
        while (admin != null) {
            System.out.println("Choose an option:");
            System.out.println("[1]View news");
            System.out.println("[2]View email;");
            System.out.println("[3]Write a message;");
            System.out.println("[4]View logs");
            System.out.println("[5]Ban user;");
            System.out.println("[6]Unban user;");
            System.out.println("[7]Exit.");
            int command = InputVerification.intValueCheck(input.readLine());
            if (command == 1) {
                admin.viewAllNews();
                System.out.println("Choose an option:");
                System.out.println("[1]Make comment");
                System.out.println("[2]Back;");
                command = InputVerification.intValueCheck(input.readLine());
                if(command==1){
                    admin.makeComment(input);
                }
                else if (command==2) {
                    continue;
                }
                else {
                    System.out.println("Wrong number!");
                }
            } else if (command == 2) {
                admin.viewAllMessages();
            } else if (command == 3) {
                admin.writeMessage(input);
            } else if (command == 4) {
                admin.readLogs();
            } else if (command == 5) {
                SchduleDrawer.printUsersForSystem(users);
                System.out.println("Choose number of the user:");
                int index = InputVerification.intValueCheck(input.readLine());
                if(index < 0 || index > users.size()){
                    System.out.println("WRONG NUMBER!");
                }
                else {
                    admin.banUser(((User)users.toArray()[index-1]));
                }
            } else if (command == 6) {
                SchduleDrawer.printUsersForSystem(users);
                System.out.println("Choose number of the user:");
                int index = InputVerification.intValueCheck(input.readLine());
                if(index < 0 || index > users.size()){
                    System.out.println("WRONG NUMBER!");
                }
                else {
                    admin.unbanUser(((User)users.toArray()[index-1]));
                }
            } else if (command == 7) {
               admin=null;
            }
            else {
                System.out.println("WRONG NUMBER!");
            }
        }
    }
}
