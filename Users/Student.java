package Users;

import Core.Course;
import Core.Intronet;
import Core.Schedule;
import Enums.Degree;
import Enums.Faculty;
import Enums.Role;
import Users.User;

import java.util.HashMap;
import java.util.Vector;

public class Student extends User {
    private Faculty faculty;
    private int yearOfStudy;
    private Degree degree;

    public Vector<String> courses;

    public int courseNameLenght;
    public int courseIdName;

    Schedule schedule;
    public Student(String login, String password, String name, String surname, Role role, Faculty faculty,Degree degree, int yearOfStudy) {
        super(login, password, name, surname,role, faculty);
        this.faculty=faculty;
        this.degree=degree;
        this.yearOfStudy=yearOfStudy;
        this.schedule = new Schedule();
        this.courses = new Vector<>();
    }
    public int maxCourseName(){
        int maxLength = 0;
        for(String courseId:courses){
            Course course = Intronet.getCourseById(courseId);
            String name = course.name;
            if(maxLength<name.length()){
                maxLength = name.length();
            }
        }
        return maxLength;
    }
    public int maxCourseId(){
        int maxLength = 0;
        for(String courseId:courses){
            Course course = Intronet.getCourseById(courseId);
            String id = course.getId();
            if(maxLength<id.length()){
                maxLength = id.length();
            }
        }
        return maxLength;
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
}
