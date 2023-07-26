package Users;

import Core.Schedule;
import Enums.Degree;
import Enums.Faculty;
import Enums.Role;
import Users.User;

import java.util.HashMap;
public class Student extends User {
    private Faculty faculty;
    private int yearOfStudy;
    private Degree degree;

    Schedule schedule;
    public Student(String login, String password, String name, String surname, Role role, Faculty faculty,Degree degree, int yearOfStudy) {
        super(login, password, name, surname,role, faculty);
        this.faculty=faculty;
        this.degree=degree;
        this.yearOfStudy=yearOfStudy;
        this.schedule = new Schedule();
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
