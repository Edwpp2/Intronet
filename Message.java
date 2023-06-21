public class Message {
    User userSource;
    String content;

    public Message(User userSource, String content,User destUser) {
        this.userSource = userSource;
        this.content = content;
        destUser.messages.add(this);
    }
    public String toString(){
        return userSource.toString() + content;
    }
}
