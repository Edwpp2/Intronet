package Core;

import Enums.Faculty;
import Users.Student;
import Users.Teacher;

import java.util.HashMap;
import java.util.Vector;
public class Course extends Discipline {

    Teacher teacher;
    String id;
    Vector<String> materials;
    HashMap<Student,Mark> studentMarks;
    int capacity;

    public Course(Faculty faculty, String title, String description, int credits, int capacity) {
        super(faculty, title, description, credits);
        this.capacity = capacity;
        studentMarks = new HashMap<>();

    }
    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }


}
