import Enums.Department;
import Enums.Role;

import java.util.HashMap;
import java.util.HashSet;

public class Teacher extends User{
    HashSet<Course> currentCourses;
    Department department;
    String[][] schedule;

    HashMap<Course,Double> courseWithRaiting;
    double raiting;
    {
        schedule = ScheduleFormer.createSchdule();
    }

    public Teacher(String login, String password, String name, String surname, String id, Role role, Department department) {
        super(login, password, name, surname, id, role, department);
        this.department = department;
    }

    public boolean checkTimeCollision(Lesson lesson){
        boolean cohesion = false;
        for(Course course : currentCourses){
            for(Lesson lessonInSystem : course.lessons)
            {
                if(!lessonInSystem.time.isAfter(lesson.time) && !lessonInSystem.time.isBefore(lesson.time))
                {
                    cohesion = true;
                    break;
                }
            }
        }
        return cohesion;
    }
    public void putMark(Course course,Student student,int part,double mark){
        for(Student student1 : course.studentsAndMarks.keySet()) {
            if(student1.equals(student)){
                double[] bufferList = student1.currentCourses.get(course);
                bufferList[part] = mark;
                student1.currentCourses.put(course,bufferList);
            }
        }
    }
    public void getMarks(Course course,Student student){
        for(Student student1 : course.studentsAndMarks.keySet())
        {
            if(student1.equals(student)){
                for (int i = 0;i < course.studentsAndMarks.get(student).length;i++){
                    System.out.println(course.studentsAndMarks.get(student)[i]);
                }
            }
        }
    }
    public void viewSchedule(){
        ScheduleFormer.viewSchedule(schedule);
    }
    public void addMatrial(Course course,String material){
        course.materials.add(material);
    }
    public void removeMaterial(int num, Course course){
        course.materials.remove(num);
    }
}
