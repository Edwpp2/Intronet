package Users;

import Core.Course;
import Core.Intronet;
import Core.Lesson;
import Core.Request;
import Enums.Faculty;
import Enums.RequestState;
import Enums.Role;

import java.io.Serializable;

public class Manager extends User implements Serializable {
    public Manager(String login, String password, String name, String surname,Role role, Faculty faculty) {
        super(login, password, name, surname,role, faculty);

    }
    public void applyRequest(Request request){
        if(request.requestType.name().equals("ADDCOURSE")){
            if(Intronet.getUserById(request.sourseId).role==Role.TEACHER){
                Teacher teacher = (Teacher) Intronet.getUserById(request.sourseId);
                Course course = Intronet.getCourseById(request.courseId);
                if(course==null|teacher==null)
                {
                    System.out.println("NO SUCH USER!");
                }
                else {
                    Intronet.addTeacherToCourse(course,teacher);
                }
            }
            else if(Intronet.getUserById(request.sourseId).role==Role.STUDENT){
                Student student = (Student) Intronet.getUserById(request.sourseId);
                Course course = Intronet.getCourseById(request.courseId);
                if(course==null||student==null)
                {
                    System.out.println("NO SUCH USER!");
                }
                else {
                    Intronet.addStudentToCourse(student,course);
                }
            }
        }
        else if(request.requestType.name().equals("DROPCOURSE")){
            if(Intronet.getUserById(request.sourseId).role==Role.TEACHER){
                Teacher teacher = (Teacher) Intronet.getUserById(request.sourseId);
                Course course = Intronet.getCourseById(request.courseId);
                if(course==null|teacher==null)
                {
                    System.out.println("NO SUCH USER!");
                }
                else {
                    Intronet.dropTeacherFromCourse(course,teacher);
                }
            }
            else if(Intronet.getUserById(request.sourseId).role==Role.STUDENT){
                Student student = (Student) Intronet.getUserById(request.sourseId);
                Course course = Intronet.getCourseById(request.courseId);
                if(course==null||student==null)
                {
                    System.out.println("NO SUCH USER!");
                }
                else {
                    Intronet.dropStudentFromCourse(student,course);
                }
            }
        }
        request.requestState= RequestState.ACCEPT;
        Intronet.requests.remove(request);
    }
    public void rejectRequest(Request request){
        request.requestState= RequestState.REJECT;
        Intronet.requests.remove(request);
    }
}
