import java.util.HashSet;
import java.util.Vector;

public class Intronet {
    static HashSet<Lesson> lessons;
    static Vector<Course> courses;
    static HashSet<User> users;
    static Vector<Request> requests;
    static Vector<News> news;

    static Vector<Discipline> disciplines;
    public static boolean checkLessonCohesion(Lesson lesson){
        for(Lesson lesson1 : lessons){
            if(lesson.hasCoheision(lesson1))
                return true;
        }
        return false;
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

}
