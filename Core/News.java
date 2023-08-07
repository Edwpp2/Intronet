package Core;
import java.util.HashMap;

public class News {
    String title;
    String content;
    public HashMap<String,String> comments;
    News(){}
    public News(String title, String content){
        this.title=title;
        this.content=content;
    }
    public String toString(){
        return title +"\n\n" + content+".";
    }
    public void setContent(String content){
        this.content = content;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public void displayComments(){
        if(comments.size()>0){
            for(String id : comments.keySet()){
                String userName = Intronet.getUserById(id).name + " " + Intronet.getUserById(id).surname;
                String comment = comments.get(id);
                System.out.println(userName+"\n");
                System.out.println(comment);
            }
        }
        else {
            System.out.println("No comments!");
        }
    }

}
