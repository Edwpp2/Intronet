import Enums.Department;
import java.util.HashMap;
import java.util.Vector;
public class Course extends Discipline{

    Teacher teacher;
    Vector<Lesson> lessons;
    int capacity;
    HashMap<Student, double[]> studentsAndMarks;
    {
        studentsAndMarks = new HashMap<>();
        lessons = new Vector<>();
        materials = new Vector<>();
    }
    Vector<String> materials;
    public Course(Department department, String title, String description, int credits, int capacity) {
        super(department, title, description, credits);
        this.capacity = capacity;
    }
    public void addStudentToCourse(Student student)
    {
        if(!student.checkTimeCohesion(this)&& student.enoughCredits(this)){
            student.minusCredits(this.credits);
            studentsAndMarks.put(student,new double[4]);
            student.currentCourses.put(this,new double[4]);
        }
        else
        {
            System.out.println("Student has time cohesion");
        }
    }
    public void setTeacher(Teacher teacher){
        this.teacher= teacher;
    }
    public void removeStudentFromCourse(Student student){
        studentsAndMarks.remove(student);
        student.currentCourses.remove(this);
        student.addCredits(this.credits);
    }
    public void addLesson(Lesson lesson){

        if(this.teacher!=null){
            if(!Intronet.checkCohision(lesson) && !this.teacher.checkTimeCollision(lesson)) {
                lessons.add(lesson);
            }
            else if(this.teacher.checkTimeCollision(lesson)){
                System.out.println(this.teacher.toString() + "has lesson which has cohesion with this lesson");
            }
        }
        else
        {
            lessons.add(lesson);
        }
    }
    public void dropLesson(Lesson lesson){
        lessons.remove(lesson);
    }
    public void addTeacher(Teacher teacher){

        for(Lesson lesson : lessons ){
            if(this.teacher.checkTimeCollision(lesson)){
                System.out.println(this.teacher.toString() + "has lesson which has cohesion with this lesson");
            }
            else {
                this.teacher = teacher;
            }
        }
    }
    public void removeTeacher(Teacher teacher){
        if(this.teacher!=null){
            this.teacher = null;
        }
    }
    public String toString(){
        return teacher.name + teacher.surname + this.title;
    }
    public void viewMatrials(){
        for (String material : materials){
            System.out.println(material);
        }
    }
}
