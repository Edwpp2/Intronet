import Enums.Department;
import Enums.RequestTypes;
import Enums.Role;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Vector;

public class Student extends User {
    int course;
    int credits;
    String[][] schedule;
    {
        currentCourses = new HashMap<>();
        schedule = ScheduleFormer.createSchdule();
    }
    HashMap<Course,double[]> currentCourses;
    HashMap<Integer,Course> passedCourse;
    public Student(String login, String password, String name, String surname, String id, Role role, Department department, int course) {
        super(login, password, name, surname, id, role, department);
        this.course = course;
    }
    public boolean checkTimeCohesion(Course course){
        boolean cohesion = false;
        for(Course studentCourses : currentCourses.keySet()){
            for (Lesson lesson : studentCourses.lessons){
                for (Lesson courseLesson : course.lessons)
                {
                    if(!lesson.time.isAfter(courseLesson.time) && !lesson.time.isBefore(courseLesson.time))
                    {
                        cohesion = true;
                        break;
                    }
                }
            }
        }
        return cohesion;
    }
    public void viewCourseInfo(Course course){
        System.out.println(course.title);
        System.out.println(course.teacher);
        for(double mark:currentCourses.get(course)){
            System.out.println(mark);
        }
    }
    public void passCourse(Course course){
        currentCourses.remove(course);
        passedCourse.put(this.course,course);
    }
    public Vector<Teacher> getTeacher(){
        Vector<Teacher> teachers = null;
        for(Course course : currentCourses.keySet()){
            teachers.add(course.teacher);
        }
        return teachers;
    }
    public void addCredits(int credits){
        this.credits+=credits;
    }
    public void minusCredits(int credits){
        this.credits-=credits;
    }
    public boolean enoughCredits(Course course)
    {
        return this.credits - course.credits >=0;
    }
    public void viewSchedule(){
        ScheduleFormer.viewSchedule(schedule);
    }
    public void addCourse(Course course){
        String content = this.toString() + "Add course:" + " " + course.toString();
        Request request = new Request(content,this.department,this, RequestTypes.ADDCOURSE);
    }
    public void dropCourse(){
        String content = this.toString() + "Remove course;";
        Request request = new Request(content,this.department,this, RequestTypes.DROPCOURSE);
    }
    public void viewTranscript(){
        for(Integer course: passedCourse.keySet()){
            System.out.println(course + " " + passedCourse.get(course));
        }
    }
}
