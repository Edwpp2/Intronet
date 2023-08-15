package Core;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;

public class News implements Serializable {
    public String title;
    public String content;
    public HashMap<String,String> comments;
    News(){}

    public News(String title, String content){
        this.title=title;
        this.content=content;
        this.comments = new HashMap<>();
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
//    public void displayComments(){
//        if(comments.size()>0){
//            for(String id : comments.keySet()){
//                String userName = Intranet.getInstance().getUserById(id).name + " " + Intranet.getInstance().getUserById(id).surname;
//                String comment = comments.get(id);
//                System.out.println(userName+"\n");
//                System.out.println(comment);
//            }
//        }
//        else {
//            System.out.println("No comments!\n");
//        }
//    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof News)) return false;
        News news = (News) o;
        return title.equals(news.title) && content.equals(news.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, content);
    }

}
