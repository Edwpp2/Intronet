package Users;

import Enums.Faculty;
import Enums.Role;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Admin extends User {
    public Admin(String login, String password, String name, String surname, Role role, Faculty faculty) {
        super(login, password, name, surname,role, faculty);
    }
    public void readLogs(){
        String filePath = "output.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void banUser(User user){
        if(!user.blocked){
            user.blocked = true;
        }
        else {
            System.out.println("User already banned");
        }
    }
    public void unbanUser(User user){
        if(user.blocked){
            user.blocked = false;
        }
        else {
            System.out.println("User not banned");
        }
    }
}
