package Core;
import Enums.Faculty;
import Users.Student;
import Users.Teacher;
import Users.User;

import java.util.HashMap;
import java.util.Vector;
public class Course extends Discipline {
    String id;
    public Vector<String> materials;
    public HashMap<String,Mark> studentMarks;
    Vector<Lesson> lessons;
    public String name;
    public int capacity;
    Schedule schedule;


    public Course(Faculty faculty, String title, String description, int credits, int capacity) {
        super(faculty, title, description, credits);
        this.capacity = capacity;
        this.schedule = new Schedule();
        this.name = title;
        this.studentMarks = new HashMap<>();
        this.materials = new Vector<>();

    }
    public Vector<Student> StudentsOnCourse(){
        Vector<Student> students = new Vector<>();
        for(String id : studentMarks.keySet()){
            students.add((Student) Intronet.getUserById(id));
        }
        return students;
    }

    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }

    public int maxMaterialName(){
        int maxLength = 0;
        for(String material : materials){
            if(maxLength < material.length()){
                maxLength = material.length();
            }
        }
        return maxLength;
    }
    public int maxUserName(){
        int maxLength = 0;
        for(String studentId:studentMarks.keySet()){
            Student student =(Student) Intronet.getUserById(studentId);
            String name = student.name + " " + student.surname;
            if(maxLength<name.length()){
                maxLength = name.length();
            }
        }
        return maxLength;
    }



}
