package Core;

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
    public String sourceId;
    public Faculty faculty;
    public String courseId;
    public String userId;

    public String operationName;

    public Request(String courseId, String userId, RequestType requestType,Faculty faculty){
        this.requestType = requestType;
        this.requestState = RequestState.PROCESSING;
        this.sourceId = userId;
        this.courseId = courseId;
        Course course = Intranet.getInstance().getCourseById(courseId);
        User user = Intranet.getInstance().getUserById(userId);
        operationName = requestType.name().equals("ADDCOURSE")?"add course":"drop course";
        this.title = course.name + operationName + user.name;
        this.faculty = faculty;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Request)) return false;
        Request request = (Request) o;
        return requestType == request.requestType && sourceId.equals(request.sourceId) && faculty == request.faculty && courseId.equals(request.courseId) && userId.equals(request.userId) && operationName.equals(request.operationName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestType, sourceId, faculty, courseId, userId, operationName);
    }
    public String toString(){
        return Intranet.getInstance().getUserById(this.sourceId).toString() + " " + operationName + Intranet.getInstance().getCourseById(courseId).name;
    }

}