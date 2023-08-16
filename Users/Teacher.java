package Users;

import Core.Course;
import Core.Intranet;
import Core.Schedule;
import Enums.Degree;
import Enums.Faculty;
import Enums.Role;


import java.io.Serializable;

import java.util.HashSet;

public class Teacher extends User implements Serializable,StudyPerson{
    public HashSet<String> courses;
    public Schedule schedule;
    public Degree degree;
    public Teacher(String login, String password, String name, String surname, Role role, Faculty faculty,Degree degree) {
        super(login, password, name, surname, role, faculty);
        this.faculty = faculty;
        this.degree = degree;
        this.schedule = new Schedule();
        this.courses = new HashSet<>();
    }
    public Schedule getSchedule() {
        return this.schedule;
    }
    @Override
    public void dropCourse(Course course) {
        Intranet.getInstance().dropTeacherFromCourse(course, this);
    }
    @Override
    public void addCourse(Course course) {
        Intranet.getInstance().addTeacherToCourse(course, this);
    }
    @Override
    public Degree getDegree() {
        return degree;
    }
}
