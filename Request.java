import Enums.Day;
import Enums.Department;
import Enums.RequestTypes;

import java.net.Authenticator;

public class Request {
    String content;
    Department department;
    RequestTypes type;
    String userId;
    String courseId;
    public Request(String content, Department department, String userId,String courseId,RequestTypes requestType){
        this.content = content;
        this.department = department;
        this. userId = userId;
        this.type = requestType;
        this.courseId = courseId;
    }
}
