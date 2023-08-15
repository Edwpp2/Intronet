package Users;

import Core.Course;
import Core.Intronet;
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
    public Teacher(String login, String password, String name, String surname, Role role, Faculty faculty) {
        super(login, password, name, surname, role, faculty);
        this.faculty = faculty;
        this.schedule = new Schedule();
        this.courses = new HashSet<>();
    }
    public Schedule getSchedule() {
        return this.schedule;
    }
    public int maxCourseName(){
        int maxLength = 0;
        for(String courseId:courses){
            Course course = Intronet.getInstance().getCourseById(courseId);
            String name = course.name;
            if(maxLength<name.length()){
                maxLength = name.length();
            }
        }
        return maxLength;
    }

    @Override
    public void dropCourse(Course course) {
        Intronet.getInstance().dropTeacherFromCourse(course, this);
    }

    @Override
    public void addCourse(Course course) {
        Intronet.getInstance().addTeacherToCourse(course, this);
    }
}
