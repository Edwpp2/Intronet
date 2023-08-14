package Core;

import Core.Course;
import Core.Intronet;
import Enums.Faculty;
import Enums.RequestState;
import Enums.RequestType;
import Users.User;
import java.io.Serializable;
import java.util.Objects;

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
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Request)) return false;
        Request request = (Request) o;
        return requestType == request.requestType && sourseId.equals(request.sourseId) && faculty == request.faculty && courseId.equals(request.courseId) && userId.equals(request.userId) && operationName.equals(request.operationName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestType, sourseId, faculty, courseId, userId, operationName);
    }
    public String toString(){
        return Intronet.getInstance().getUserById(this.sourseId).toString() + " " + operationName + Intronet.getInstance().getCourseById(courseId).name;
    }

}