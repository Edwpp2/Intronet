package Core;
import Users.User;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Vector;

public class Intronet {
    static Vector<Course> courses;
    public static HashSet<User> users;
    static Vector<Request> requests;
    static Vector<News> news;
    private static final int idLength = 6;

    public String generateUserId(){

        String year= "" + (Calendar.YEAR - 2000);
        return year + "B" + ("0".repeat(idLength - ("" + users.size()).length()) + ("" + users.size()));
    }
    public String generateCourseId() {
        String year= "" + (Calendar.YEAR - 2000);
        return (year) + "C" + ("0".repeat(idLength - ("" + courses.size()).length()) + ("" + courses.size()));
    }
    public void addCourse(Course course){
        courses.add(course);
        course.setId(this.generateCourseId());

    }
    public void addUser(User user){
        users.add(user);
        user.setId(this.generateUserId());
    }



}
