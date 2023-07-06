import Enums.Department;
import Enums.Role;

import java.util.HashMap;

public class Teacher extends User{
    HashMap<Course,double[]> currentCourses;
    Department department;
    {
        currentCourses = new HashMap<>();
    }
    public Teacher(String login, String password, String name, String surname, String id, Role role, Department department) {
        super(login, password, name, surname, id, role, department);
        this.department = department;
    }
    public void putMark(Course course,Student student,int part,double mark){
        int index = part-1;
        double[] bufferMarkArray = student.currentCourses.get(course);
        bufferMarkArray[index] = mark;
        student.currentCourses.put(course,bufferMarkArray);
    }
    public void putMaterial(Course course,String material){
        course.addMaterial(material);
    }
    public void removeMaterial(int num,Course course){
        course.removeMaterial(num);
    }
    public void viewMaterials(Course course){
        course.viewMaterial();
    }
    public void dropCourse(Course course){
        currentCourses.remove(course);
    }
    public void getRating(Course course){
        double bufferRating=0;
        for(double rating : currentCourses.get(course)){
            bufferRating+=rating;
        }
        System.out.println(course.title + " : " + bufferRating/(double) currentCourses.get(course).length);
    }
    public boolean checkCohesion(Course courseToAdd) {
        for (Course course : currentCourses.keySet()) {
            if (course.hasTimeCohesion(course.lessons, courseToAdd.lessons)) {
                return false;
            }
        }
        return true;
    }
    public void removeCourse(Course course){
        for (Course course1: currentCourses.keySet()){
            if(course.equals(course1))
            {
                currentCourses.remove(course);
            }
        }
    }
    public void addCourse(Course course){
        currentCourses.put(course,new double[course.capacity]);
    }
    public void viewCoursesList(){
        CourseAdepter.viewObjectsInArray(currentCourses.keySet().toArray(new Course[0]));
    }
    public Course getCourse(int num){
        return CourseAdepter.getObjectFromArray(CourseAdepter.toArray(currentCourses.keySet()),num);
    }
    public void viewStudentsOnCourse(Course course){
        course.viewStudentsOnCourse();
    }
    public Student getStudentFromCourse(Course course,int num){
        return course.getStudentFromCourse(num);
    }
}
