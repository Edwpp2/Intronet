import Enums.Department;
import Enums.Role;
import java.util.HashMap;
public class Student extends User {
    int course;
    int credits;
    {
        currentCourses = new HashMap<>();
        passedCourse = new HashMap<>();
    }
    HashMap<Course,double[]> currentCourses;
    HashMap<Integer,Course> passedCourse;
    public Student(String login, String password, String name, String surname, String id, Role role, Department department, int course) {
        super(login, password, name, surname, id, role, department);
        this.course = course;
    }
    public boolean enoughCredits(Course course)
    {
        return this.credits - course.credits >=0;
    }
    public void viewCoursesList(){
        CourseAdepter.viewObjectsInArray(currentCourses.keySet().toArray(new Course[0]));
    }
    public Course getCourse(int num){
        return CourseAdepter.getObjectFromArray(CourseAdepter.toArray(currentCourses.keySet()),num);
    }
    public void viewMaterials(Course course){
        course.viewMaterial();
    }
    public void addCourse(Course course){
        currentCourses.put(course,new double[4]);
    }
    public void removeCourse(Course course){
        for (Course course1: currentCourses.keySet()){
            if(course.equals(course1))
            {
                currentCourses.remove(course);
            }
        }
    }
    public boolean checkCohesion(Course courseToAdd) {
        for (Course course : currentCourses.keySet()) {
            if (course.hasTimeCohesion(course.lessons, courseToAdd.lessons)) {
                return true;
            }
        }
        return false;
    }
    public void dropCourse(Course course){
        currentCourses.remove(course);
    }
    public void viewTranscript(){
        for(Integer course: passedCourse.keySet()){
            System.out.println(course + " " + passedCourse.get(course));
        }
    }

}
