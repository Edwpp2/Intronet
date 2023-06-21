import Enums.Department;
import Enums.Role;

import java.util.Vector;

public class User {
    String login;
    String password;
    public String name;
    public String surname;
    String id;
    Role role;

    Department department;
    Vector<Message> messages;
    {messages = new Vector<Message>();}

    public User(String login, String password, String name, String surname, String id, Role role, Department department) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.id = id;
        this.role = role;
        this.department = department;
    }

    public void writeMessage(String login,String content){
        for(User user : Intronet.users){
            if(user.login.equals(login)){
                user.messages.add(new Message(this,content,user));
            }
        }
    }
    public void viewAllMessages(){
        for(Message message: messages){
            System.out.println(message.toString());
        }
    }
    public void viewNews(){
        for(News news: Intronet.news){
            System.out.println(news.toString());
        }
    }
    public boolean login(String login,String password){
        return this.login.equals(login) && this.password.equals(password);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", id='" + id + '\'' +
                ", role=" + role +
                ", department=" + department +
                '}';
    }
}
