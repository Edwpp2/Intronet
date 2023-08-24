package Users;

import Core.*;
import Enums.Degree;
import Enums.Faculty;
import Enums.Role;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;


public class Student extends User implements Serializable,StudyPerson {
    public Faculty faculty;
    public int yearOfStudy;
    public Degree degree;
    public int credits;
    public HashMap<String, Mark> courses;
    public int passedCoursesCnt;
    public int registeredCoursesCnt;
    public HashSet<String> passedCourses;
    public Schedule schedule;
    public HashMap<Integer, HashMap<String,Mark>> transcript;
    public Student(String login, String password, String name, String surname, Role role, Faculty faculty,Degree degree) {
        super(login, password, name, surname,role, faculty);
        this.faculty=faculty;
        this.degree=degree;
        this.credits = 30;
        this.yearOfStudy=1;
        this.schedule = new Schedule();
        this.courses = new HashMap<>();
        this.passedCourses = new HashSet<>();
        this.transcript = new HashMap<>();
        this.passedCoursesCnt=0;
        this.registeredCoursesCnt=0;
    }
    public int maxCourseName(){
        int maxLength = 0;
        for(String courseId:courses.keySet()){
            Course course = Intranet.getInstance().getCourseById(courseId);
            String name = course.title;
            if(maxLength<name.length()){
                maxLength = name.length();
            }
        }
        return maxLength;
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
    public void rateTeacher(Course course,int rating){
        if(course.teacherRating.get(this.getId())==null){
            if(rating > 0 && rating <= 5){
                course.teacherRating.put(this.getId(),rating);
                Logs.AddToLog("Put rating" + rating + "to" + course.toString(),this);
            }
            else {
                System.out.println("Wrong rating!It must be less or equal to 5!");
            }
        }
        else {
            System.out.println("You are already put the rating!");
            System.out.println(course.teacherRating.get(this.getId()));
        }
    }
    public void nextCourse(){
        System.out.println(courses.size());
        System.out.println(passedCourses.size());
        if (passedCoursesCnt==registeredCoursesCnt){
            courses.clear();
            passedCoursesCnt=0;
            registeredCoursesCnt=0;
            yearOfStudy++;
            credits=30;
        }
    }
    public int maxTranscriptCourseName(int yearOfStudy){
        int maxNameLength = 0;
        for (String courseId : transcript.get(yearOfStudy).keySet()){
            String courseName = Intranet.getInstance().getCourseById(courseId).name;
            if(maxNameLength < courseName.length()){
                maxNameLength = courseName.length();
            }
        }
        return maxNameLength;
    }

    @Override
    public void dropCourse(Course course) {
        Intranet.dropStudentFromCourse(this, course);
        registeredCoursesCnt--;
    }

    @Override
    public void addCourse(Course course) {
        Intranet.addStudentToCourse(this, course);
        registeredCoursesCnt++;
    }
    public Course getCourse(int courseNum){
        try{
            return  (Course) (Intranet.getInstance().courses.toArray()[courseNum-1]);
        }
        catch (NullPointerException e){
            System.out.println("WRONG NUMBER!");
        }
        return null;
    }

    @Override
    public Degree getDegree() {
        return degree;
    }

}
