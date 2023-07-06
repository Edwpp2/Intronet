import Enums.Department;
import Enums.Role;

public class Manager extends User{
    public Manager(String login, String password, String name, String surname, String id, Role role, Department department) {
        super(login, password, name, surname, id, role, department);
    }
    public void addCourse(Department department, String title, String description, int credits, int capacity){
        Intronet.courses.add(new Course(department,title,description,credits,capacity));
    }
    public void removeCourse(Course course){
        for(Student student: course.studentsAndMarks.keySet()){
            course.removeUserFromCourse(student);
        }
        if(course.teacher!=null){
            course.removeUserFromCourse(course.teacher);
        }
        for(Lesson lesson: course.lessons){
            Intronet.lessons.remove(lesson);
        }
        Intronet.courses.remove(course);
    }
    public void addUserToCourse(User user,Course course){
        course.addUserToCourse(user);
    }
    public void removeUserFromCourse(User user,Course course){
        course.removeUserFromCourse(user);
    }
    public void AddNews(String title,String content){
        Intronet.news.add(new News(title,content));
    }
}
