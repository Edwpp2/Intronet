package Core;
import Enums.Faculty;
import Users.Student;
import Users.Teacher;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Vector;
public class Course extends Discipline implements Serializable {
    public String id;
    public Vector<Material> materials;
    public HashMap<String,Mark> studentMarks;
    public HashMap<String,Integer> teacherRating;
    public Teacher teacher;
    public Vector<Lesson> lessons;
    public String name;
    public int capacity;
    public Schedule schedule;
    public Faculty faculty;
    public int credits;
    public HashSet<String> prerecs;

    public Course(Faculty faculty, String title, String description, int credits, int capacity,String code) {
        super(faculty, title, description, credits,code);
        this.capacity = capacity;
        this.faculty = faculty;
        this.name = title;
        this.credits = credits;
        this.studentMarks = new HashMap<>();
        this.materials = new Vector<>();
        this.prerecs = new HashSet<>();
        this.lessons = new Vector<>();
        this.teacherRating = new HashMap<>();
        this.schedule = new Schedule();
    }
    public Vector<Student> StudentsOnCourse(){
        Vector<Student> students = new Vector<>();
        for(String id : studentMarks.keySet()){
            students.add((Student) Intranet.getInstance().getUserById(id));
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
        for(Material material : materials){
            if(maxLength < material.title.length()){
                maxLength = material.title.length();
            }
        }
        return maxLength;
    }
    public boolean hasPrerec(Student student){
        int prercCnt = 0;
        for (String course : prerecs){
            for(int yearsOfStudy : student.transcript.keySet()){
                if(student.transcript.get(yearsOfStudy).containsKey(course))
                {
                    prercCnt++;
                }
            }
        }
        return prerecs.size()==prercCnt;
    }
    public boolean canJoin(Student student){
        return (student.credits-this.credits >=0) && this.studentMarks.size()+1<=this.capacity && hasPrerec(student);
    }
    public double getTeacherRating(){
        double ratingAvg = 0.0;
        if(this.teacherRating.keySet().size()!=0){
            for (String id : teacherRating.keySet()){
                ratingAvg+= teacherRating.get(id);
            }
            return ratingAvg/teacherRating.keySet().size();
        }
        return 0.0;
    }
}
