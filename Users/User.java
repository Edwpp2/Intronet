package Users;

import Enums.Faculty;
import Enums.Role;
import Core.*;

import java.util.Calendar;
import java.util.Vector;

public class User {
    boolean blocked;
    public String login;
    public String password;
    public String name;
    public String surname;
    private String id;
    public Role role;
    Faculty faculty;
    public Vector<Message> messages;
    {messages = new Vector<Message>();}

    public User(String login, String password, String name, String surname, Role role, Faculty faculty) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.role = role;
        this.faculty = faculty;
    }
    public void writeMessage(String login,String content){
        for(User user : Intronet.users){
            if(user.login.equals(login)){
                user.messages.add(new Message(this,content));
            }
        }
    }
    public void viewAllMessages(){
        for(Message message: messages){
            System.out.println(message.toString());
        }
    }
    public String getId(){
        return this.id;
    }
    public void  setId(String id){
        this.id = id;
    }

    public boolean isBlocked(){
        return this.blocked;
    }
}
