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
        for(Lesson lesson: course.lessons){
            Intronet.lessons.remove(lesson);
        }
        Intronet.courses.remove(course);
    }
    public void addStudentToCourse(Student student,Course course){
        course.addStudentToCourse(student);
    }
    public void removeStudentFromCourse(Student student,Course course){
        course.removeStudentFromCourse(student);
    }
    public void AddNews(String title,String content){
        Intronet.news.add(new News(title,content));
    }
    public void addTeacherToCourse(Teacher teacher,Course course){
        course.setTeacher(teacher);
    }
}
