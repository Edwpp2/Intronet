package Core;


import java.io.Serializable;
import java.util.Objects;

public class Message implements Cloneable, Serializable {
    String userSourceLogin;
    String content;

    public Message(String userSourceLogin, String content){
        this.userSourceLogin = userSourceLogin;
        this.content = content;
    }
    public String toString(){
        return "From:" + Intranet.getInstance().getUserByLogin(userSourceLogin).toString() + "\n\n\n" + content;
    }
    @Override
    public Message clone() {
        try {
            return (Message) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message message)) return false;
        return userSourceLogin.equals(message.userSourceLogin) && content.equals(message.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userSourceLogin, content);
    }
}
