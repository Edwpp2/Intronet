package Core;
import Enums.Faculty;
import Users.Student;
import Users.Teacher;
import Users.User;

import java.util.HashMap;
import java.util.Vector;
public class Course extends Discipline {
    String id;
    Vector<String> materials;
    HashMap<String,Mark> studentMarks;
    Vector<Lesson> lessons;
    String name;
    int capacity;

    Schedule schedule;

    public Course(Faculty faculty, String title, String description, int credits, int capacity) {
        super(faculty, title, description, credits);
        this.capacity = capacity;
        this.schedule = new Schedule();

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


}
