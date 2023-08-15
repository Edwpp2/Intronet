package Users;

import Core.*;
import Enums.Faculty;
import Enums.RequestType;
import Enums.Role;
import java.io.Serializable;
import java.util.Vector;

public class Manager extends User implements Serializable {
    public Manager(String login, String password, String name, String surname, Role role, Faculty faculty) {
        super(login, password, name, surname, role, faculty);

    }
    public void applyRequest(Request request)
    {
        Intranet intranet = Intranet.getInstance();
        User user = intranet.getUserById(request.sourceId);
        Course course = intranet.getCourseById(request.courseId);
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
        Intranet.getInstance().requests.remove(request);
        User user =  Intranet.getInstance().getUserById(request.sourceId);
        Logs.AddToLog("Reject request from " + user.name + " " + user.surname,this);
    }
    public void addNews(String title,String content){
        Intranet.getInstance().news.add(new News(title,content));
    }
    public News getNews(int number){
        Vector<News> news = Intranet.getInstance().getInstance().news;
        return news.get(number-1);
    }
    public void removeNews(int number){
        Vector<News> news = Intranet.getInstance().getInstance().news;
        news.remove(number-1);
    }
}
