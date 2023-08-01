package Users;
import Core.Course;
import Core.Intronet;
import Core.Lesson;
import Core.Request;
import Enums.Faculty;
import Enums.RequestState;
import Enums.Role;

public class Manager extends User {
    public Manager(String login, String password, String name, String surname, String id, Role role, Faculty faculty) {
        super(login, password, name, surname,role, faculty);
        this.faculty = faculty;
    }
    public void applyRequest(Request request){
        if(request.requestType.name().equals("ADDCOURSE")){
            Student student = (Student) Intronet.getUserById(request.sourseId);
            Course course = Intronet.getCourseById(request.courseId);
            Intronet.addStudentToCourse(student,course);
        }
        else if(request.requestType.name().equals("DROPCOURSE")){
            Student student = (Student) Intronet.getUserById(request.sourseId);
            Course course = Intronet.getCourseById(request.courseId);
            Intronet.dropStudentFromCourse(student,course);
        }
        request.requestState= RequestState.ACCEPT;
        Intronet.requests.remove(request);
    }
    public void rejectRequest(Request request){
        request.requestState= RequestState.REJECT;
        Intronet.requests.remove(request);
    }
    public void addLessonToCourse(Course course,Lesson lesson){
        Intronet.addLessonToCourse(course,lesson);
    }
    public void dropLessonFromCourse(Course course,Lesson lesson){
        Intronet.dropLessonToCourse(course,lesson);
    }
}
