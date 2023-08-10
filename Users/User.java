package Users;

import Enums.Faculty;
import Enums.Role;
import Core.*;
import java.util.Objects;
import java.util.Vector;

public class User {
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
        messages = new Vector<Message>();
    }
    public void writeMessage(String login,String content){
        for(User user : Intronet.users){
            if(user.login.equals(login)){
                user.messages.add(new Message(this,content));
            }
        }
    }
    public News getNews(News news,int i){
        return CourseAdepter.getObjectFromArray(CourseAdepter.toArray(Intronet.news),i);
    }
    public void makeComment(News news,String comment){
        news.comments.put(this.id,comment);
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
}
