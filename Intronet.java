import java.util.HashSet;
import java.util.Vector;

public class Intronet {
    static HashSet<Lesson> lessons;
    static Vector<Course> courses;
    static HashSet<User> users;
    static Vector<Request> requests;
    static Vector<News> news;

    static Vector<Discipline> disciplines;
    public static boolean checkCohision(Lesson lesson){
        boolean cohesion = false;
        for(Lesson lessonInSystem : lessons)
        {
            if(!lessonInSystem.time.isAfter(lesson.time) && !lessonInSystem.time.isBefore(lesson.time) && lessonInSystem.room.equals(lesson.room))
            {
                cohesion = true;
                break;
            }
        }
        return cohesion;
    }
    public static User login(String login,String password){
        for(User user : users)
        {
            if(user.login(login,password)){
                return user;
            }
        }
        return null;
    }
    public static void viewNews(){
        int i = 1;
        for (News news : news){
            System.out.println(i+ " " + news.title);
            i++;
        }
    }

}
