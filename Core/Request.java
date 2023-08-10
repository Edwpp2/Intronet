package Core;

import Core.Course;
import Core.Intronet;
import Enums.Faculty;
import Enums.RequestState;
import Enums.RequestType;
import Users.User;

import java.io.Serializable;

import static Core.Intronet.getUserById;

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
        Course course = Intronet.getCourseById(courseId);
        User user = getUserById(userId);
        operationName = requestType.name().equals("ADDCOURSE")?"add course":"drop course";
        this.title = course.name + operationName + user.name;
        this.faculty = faculty;
    }

    public String toString(){
        return Intronet.getUserById(this.userId).toString() + " " + operationName + Intronet.getCourseById(courseId).name;
    }
}