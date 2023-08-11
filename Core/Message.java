package Core;


import java.io.Serializable;

public class Message implements Cloneable, Serializable {
    String userSourceLogin;
    String content;
    public Message(String userSourceLogin, String content){
        this.userSourceLogin = userSourceLogin;
        this.content = content;
    }
    public String toString(){
        return "From:" + Intronet.getInstance().getUserByLogin(userSourceLogin).name + " " + Intronet.getInstance().getUserByLogin(userSourceLogin).surname + "\n\n\n" + content;
    }
    @Override
    public Message clone() {
        try {
            Message clone = (Message) super.clone();

            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
