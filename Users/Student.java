package Users;

import Core.*;
import Enums.Degree;
import Enums.Faculty;
import Enums.RequestType;
import Enums.Role;
import Users.User;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Vector;

public class Student extends User {
    private Faculty faculty;
    private int yearOfStudy;
    private Degree degree;
    public int credits;
    public HashMap<String, Mark> courses;
    public HashSet<String> passedCourses;
    Schedule schedule;
    public Student(String login, String password, String name, String surname, Role role, Faculty faculty,Degree degree, int yearOfStudy) {
        super(login, password, name, surname,role, faculty);
        this.faculty=faculty;
        this.degree=degree;
        this.yearOfStudy=yearOfStudy;
        this.schedule = new Schedule();
        this.courses = new HashMap<>();
        this.passedCourses = new HashSet<>();
        this.credits = 30;
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
    public int getYearOfStudy(){
        return this.yearOfStudy;
    }
    public void incYearOfStudy(){
        this.yearOfStudy++;
    }
    public Degree getDegree(){
        return this.degree;
    }
    public void setDegree(Degree degree){
        this.degree=degree;
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
            System.out.println("You are alredy put the rating!");
        }
    }
}
