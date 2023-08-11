package Users;

import Core.Course;
import Core.Intronet;
import Core.Schedule;
import Enums.Degree;
import Enums.Faculty;
import Enums.Role;
import Frontend.SchduleDrawer;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;

public class Teacher extends User implements Serializable {
    public HashMap<String,HashMap<String,Double>> ratingByCourse;
    public HashSet<String> courses;
    public Schedule schedule;
    public Degree degree;

    public Teacher(String login, String password, String name, String surname, Role role, Faculty faculty,Degree degree) {
        super(login, password, name, surname, role, faculty);
        this.faculty = faculty;
        this.schedule = new Schedule();
        this.courses = new HashSet<>();
    }
    public Schedule getSchedule() {
        return this.schedule;
    }
    public void printSchedule(){
        SchduleDrawer.printSchedule(this.schedule);
    }
    public void printRatingForCourse(Course course){
        SchduleDrawer.printTeacherRatingForCourse(course,0,0);
    }
    public void printRatingForAllCourses(){
        SchduleDrawer.printTeacherRatingForAllCourses(this);
    }
    public void printListOfCourses(){
        SchduleDrawer.printInfoAboutTeacherCourses(this);
    }
    public void printMarksForListOfStudents(Course course){
        SchduleDrawer.printMarksForListOfStudents(course);
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
    public Course getCourseFromList(int i){
        Course course = Intronet.getInstance().getCourseById((String) courses.toArray()[i-1]);
        return course;
    }





}
