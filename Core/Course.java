package Core;
import Enums.Faculty;
import Users.Student;
import Users.Teacher;


import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Vector;
public class Course extends Discipline {
    String id;
    public Vector<String> materials;
    public HashMap<String,Mark> studentMarks;
    public HashMap<String,Double> teacherRating;
    Teacher teacher;
    public Vector<Lesson> lessons;
    public String name;
    public int capacity;
    Schedule schedule;
    public Faculty faculty;
    public int credits;
    public HashSet<String> prerecs;

    public Course(Faculty faculty, String title, String description, int credits, int capacity,String code) {
        super(faculty, title, description, credits,code);
        this.capacity = capacity;
        this.faculty = faculty;
        this.name = title;
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
            students.add((Student) Intronet.getUserById(id));
        }
        return students;
        int[] arr = new int[];
        Arrays.stream(arr).sorted()\
        Arrays.stream(arr).sorted();
        System.out.println(arr);
        for(int i = 0 ; i < arr.length;i++){
            System.out.println(arr[i]);
        }
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
    public boolean hasPrerec(Student student){
        for (String course : prerecs){
            if(!student.passedCourses.contains(course)){
                return false;
            }
        }
        return true;
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
