import Enums.Day;
import Enums.Department;
import Enums.RequestTypes;

import java.net.Authenticator;

public class Request {
    String content;
    Department department;
    User userSource;
    RequestTypes type;
    public Request(String content, Department department, User userSource,RequestTypes type){
        this.content = content;
        this.department = department;
        this. userSource = userSource;
        this.type = type;
    }
}
