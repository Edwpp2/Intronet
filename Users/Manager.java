package Users;

import Core.*;
import Enums.Faculty;
import Enums.RequestType;
import Enums.Role;
import java.io.Serializable;

public class Manager extends User implements Serializable {
    public Manager(String login, String password, String name, String surname, Role role, Faculty faculty) {
        super(login, password, name, surname, role, faculty);

    }
    public void applyRequest(Request request)
    {
        Intronet intronet = Intronet.getInstance();
        User user = intronet.getUserById(request.sourseId);
        Course course = intronet.getCourseById(request.courseId);
        if (course == null | user == null) {
            System.out.println("NO SUCH USER!");
            return;
        }
        StudyPerson person = (StudyPerson) user;
        if (request.requestType == RequestType.ADDCOURSE) {
            person.addCourse(course);
        }
        else if (request.requestType == RequestType.DROPCOURSE) {
            person.dropCourse(course);
        }
        String requestType = request.requestType==RequestType.ADDCOURSE?"add":"drop";
        Logs.AddToLog("Accept " + requestType + " request from " + user.name +" "+ user.surname, this);
    }
    public void rejectRequest(Request request){
        Intronet.getInstance().requests.remove(request);
        User user =  Intronet.getInstance().getUserById(request.sourseId);
        Logs.AddToLog("Reject request from " + user.name + " " + user.surname,this);
    }
    public void addNews(String title,String content){
        Intronet.getInstance().news.add(new News(title,content));
    }
    public News getNews(int number){
        return Intronet.getInstance().getInstance().news.get(number-1);
    }
    public void removeNews(int number){
        Intronet.getInstance().news.remove(number-1);
    }
}
