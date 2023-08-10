package Users;

import Core.*;
import Enums.Degree;
import Enums.Faculty;
import Enums.RequestType;
import Enums.Role;
import Frontend.SchduleDrawer;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;


public class Student extends User implements Serializable {
    public Faculty faculty;
    private int yearOfStudy;
    public Degree degree;
    public int credits;
    public HashMap<String, Mark> courses;
    public HashSet<String> passedCourses;
    Schedule schedule;
    public Student(String login, String password, String name, String surname, Role role, Faculty faculty,Degree degree) {
        super(login, password, name, surname,role, faculty);
        this.faculty=faculty;
        this.degree=degree;
        this.credits = 30;
        this.yearOfStudy=1;
        this.schedule = new Schedule();
        this.courses = new HashMap<>();
        this.passedCourses = new HashSet<>();
    }
    public int maxCourseName(){
        int maxLength = 0;
        for(String courseId:courses.keySet()){
            Course course = Intronet.getCourseById(courseId);
            String name = course.name;
            if(maxLength<name.length()){
                maxLength = name.length();
            }
        }
        return maxLength;
    }
    public void makeRequest(Course course, RequestType requestType){
        Request request = new Request(course.getId(),this.getId(),requestType,this.faculty);
        Intronet.requests.add(request);
    }
    public Faculty getFaculty(){
        return this.faculty;
    }
    public void setFaculty(Faculty faculty){
        this.faculty = faculty;
    }
    public Schedule getSchedule() {
        return this.schedule;
    }
    public void rateTeacher(Course course,Double rating){
        if(course.teacherRating.get(this.getId())!=null){
            if(rating<=5.0){
                course.teacherRating.put(this.getId(),rating);
            }
            else {
                System.out.println("Wrong rating!It must be less or equal to 5!");
            }
        }
        else {
            System.out.println("You are already put the rating!");
        }
    }
}
