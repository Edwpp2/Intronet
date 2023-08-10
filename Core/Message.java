package Core;

import Users.User;

public class Message {
    User userSource;
    String content;
    public Message(User userSource, String content){
        this.userSource = userSource;
        this.content = content;
    }
    public String toString(){
        return "From:" + userSource.name + " " + userSource.surname + "\n\n\n" + content;
    }
}
