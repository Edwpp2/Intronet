package Core;

import Core.Course;
import Core.Intronet;
import Enums.Faculty;
import Enums.RequestState;
import Enums.RequestType;
import Users.User;
import java.io.Serializable;

public class Request implements Serializable {
    public String title;
    public RequestType requestType;
    public RequestState requestState;
    public String sourseId;
    public Faculty faculty;
    public String courseId;
    public String userId;

    public String operationName;

    public Request(String courseId, String userId, RequestType requestType,Faculty faculty){
        this.requestType = requestType;
        this.requestState = RequestState.PROCESSING;
        this.sourseId = userId;
        this.courseId = courseId;
        Course course = Intronet.getInstance().getCourseById(courseId);
        User user = Intronet.getInstance().getUserById(userId);
        operationName = requestType.name().equals("ADDCOURSE")?"add course":"drop course";
        this.title = course.name + operationName + user.name;
        this.faculty = faculty;
    }

    public String toString(){
        return Intronet.getInstance().getUserById(this.sourseId).toString() + " " + operationName + Intronet.getInstance().getCourseById(courseId).name;
    }
}