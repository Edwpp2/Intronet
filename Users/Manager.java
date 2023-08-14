package Users;

import Core.*;
import Enums.Faculty;
import Enums.RequestState;
import Enums.RequestType;
import Enums.Role;

import java.io.Serializable;

public class Manager extends User implements Serializable {
    public Manager(String login, String password, String name, String surname,Role role, Faculty faculty) {
        super(login, password, name, surname,role, faculty);

    }
    public void applyRequest(Request request){
        if(request.requestType==RequestType.ADDCOURSE){
            if(Intronet.getInstance().getUserById(request.sourseId).role==Role.TEACHER){
                Teacher teacher = (Teacher) Intronet.getInstance().getUserById(request.sourseId);
                Course course = Intronet.getInstance().getCourseById(request.courseId);
                if(course==null|teacher==null)
                {
                    System.out.println("NO SUCH USER!");
                }
                else {
                    Intronet.getInstance().addTeacherToCourse(course,teacher);
                }
            }
            else if(Intronet.getInstance().getUserById(request.sourseId).role==Role.STUDENT){
                Student student = (Student) Intronet.getInstance().getUserById(request.sourseId);
                Course course = Intronet.getInstance().getCourseById(request.courseId);
                if(course==null||student==null)
                {
                    System.out.println("NO SUCH USER!");
                }
                else {
                    Intronet.addStudentToCourse(student,course);
                    student.registeredCoursesCnt++;
                }
            }
            Logs.AddToLog("Accept add request from" + Intronet.getInstance().getUserById(request.sourseId).name + Intronet.getInstance().getUserById(request.sourseId).surname,this);
        }
        else if(request.requestType==RequestType.DROPCOURSE){
            if(Intronet.getInstance().getUserById(request.sourseId).role==Role.TEACHER){
                Teacher teacher = (Teacher) Intronet.getInstance().getUserById(request.sourseId);
                Course course = Intronet.getInstance().getCourseById(request.courseId);
                if(course==null|teacher==null)
                {
                    System.out.println("NO SUCH USER!");
                }
                else {
                    Intronet.dropTeacherFromCourse(course,teacher);
                }
            }
            else if(Intronet.getInstance().getUserById(request.sourseId).role==Role.STUDENT){
                Student student = (Student) Intronet.getInstance().getUserById(request.sourseId);
                Course course = Intronet.getInstance().getCourseById(request.courseId);
                if(course==null||student==null)
                {
                    System.out.println("NO SUCH USER!");
                }
                else {

                    System.out.println("DROP STAGE!");
                    Intronet.dropStudentFromCourse(student,course);
                    student.registeredCoursesCnt--;
                }
            }
            Logs.AddToLog("Accept drop request from" + Intronet.getInstance().getUserById(request.sourseId).name + Intronet.getInstance().getUserById(request.sourseId).surname,this);
        }
        request.requestState=RequestState.ACCEPT;
        Intronet.getInstance().requests.remove(request);
    }
    public void rejectRequest(Request request){
        request.requestState=RequestState.REJECT;
        Intronet.getInstance().requests.remove(request);
        Logs.AddToLog("Reject request from" + Intronet.getInstance().getUserById(request.sourseId).name + Intronet.getInstance().getUserById(request.sourseId).surname,this);
    }
}
