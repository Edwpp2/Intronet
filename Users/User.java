package Users;

import Enums.Faculty;
import Enums.Role;
import Core.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.Objects;
import java.util.Vector;

public class User implements Cloneable, Serializable{
    boolean blocked;
    public String login;
    public String password;
    public String name;
    public String surname;
    private String id;
    public Role role;
    public Faculty faculty;
    public Vector<Message> messages;
    public User(String login, String password, String name, String surname, Role role, Faculty faculty) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.role = role;
        this.faculty = faculty;
        messages = new Vector<>();
    }
    public void viewAllNews(){
        if(Intranet.getInstance().news.size()>0){
            Intranet.getInstance().printNews();
        }
        else {
            System.out.println("No news!");
        }
    }
    public void makeComment(BufferedReader input) throws IOException {
        System.out.println("Enter number of news");
        try {
            int newsNumber = InputVerification.intValueCheck(input.readLine());
            News news = (News) Intranet.getInstance().news.toArray()[newsNumber-1];
            System.out.println("Enter your comment:");
            String comment = input.readLine();
            if(comment.length()>0){
                news.comments.put(this.id,comment);
            }
            else {
                System.out.println("Empty comment!");
            }
        }
        catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Wrong number!");
        }
    }
    public void viewAllMessages(){
        if(this.messages.size()>0){
            for(Message message: messages){
                System.out.println(message.toString());
            }
        }
        else {
            System.out.println("NO MESSAGES!");
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
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return login.equals(user.login) && password.equals(user.password) && id.equals(user.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(login, password, id);
    }

    @Override
    public User clone() {
        try {
            return (User) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
    public String toString(){
        return this.name + " " + this.surname;
    }
    public void writeMessage(BufferedReader input) throws IOException {
        User destUser;
        System.out.println("Enter login of user!");
        String login = input.readLine();
        destUser = Intranet.getInstance().getUserByLogin(login);
        if (destUser == null) {
            System.out.println("User not found!");
        } else {
            System.out.println("Write a text!");
            String text = input.readLine();
            Message message = new Message(login, text);
            destUser.messages.add(message);
        }
    }
}
