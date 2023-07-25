package Core;

import Enums.Faculty;
import Enums.RequestState;

public class Request {
    String content;
    Faculty faculty;
    String userId;
    RequestState requestState;
    String courseId;
    public Request(String content,Faculty faculty,String userId,String courseId){
        this.content = content;
        this.faculty = faculty;
        this.userId = userId;
        this.courseId = courseId;
        this.requestState = RequestState.PROCESSING;
    }
    public void acceptRequest(){
        this.requestState = RequestState.ACCEPT;
    }
    public void rejectRequest(){
        this.requestState = RequestState.REJECT;
    }


}
