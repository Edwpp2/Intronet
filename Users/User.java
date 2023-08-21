package Users;

import Enums.Faculty;
import Enums.Role;
import Core.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.Objects;
import java.util.Vector;

/**
 *
 */
public class User implements Cloneable, Serializable{

    private boolean blocked;
    private String login;
    private String password;
    private String name;
    private String surname;
    private String id;
    private Role role;
    private Faculty faculty;
    private Vector<Message> messages;

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
    public String toString(){
        return this.name + " " + this.surname + this.role.name();
    }
    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Role getRole() {
        return role;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public Vector<Message> getMessages() {
        return messages;
    }

    public void setMessages(Vector<Message> messages) {
        this.messages = messages;
    }
}
