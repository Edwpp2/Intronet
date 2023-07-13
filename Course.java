import Enums.Department;
import java.util.HashMap;
import java.util.Vector;
public class Course extends Discipline{

    Teacher teacher;
    Vector<Lesson> lessons;
    Vector<String> materials;
    int capacity;
    HashMap<Student, double[]> studentsAndMarks;
    {
        studentsAndMarks = new HashMap<>();
        lessons = new Vector<>();
        materials = new Vector<>();
    }

    public Course(Department department, String title, String description, int credits, int capacity) {
        super(department, title, description, credits);
        this.capacity = capacity;
    }
    public void addUserToCourse(User user){
        if(user.getClass().getTypeName().equals("Student")){
            Student student = (Student) user;
            if(!student.checkCohesion(this)){
                student.addCourse(this);
                studentsAndMarks.put(student,new double[4]);
                for(Lesson lesson: lessons){
                    student.schedule.addToSchedule(lesson);
                }
            }
            else {
                System.out.println("This student has time cohesion in schedule!");
            }
        } else if (user.getClass().getTypeName().equals("Teacher")) {
            Teacher teacher = (Teacher) user;
            if(teacher.checkCohesion(this)){
                teacher.addCourse(this);
                this.teacher = teacher;
                for(Lesson lesson: lessons){
                    teacher.schedule.addToSchedule(lesson);
                }
            }
            else {
                System.out.println("This teacher has time cohesion in schedule!");
            }
        }
    }
    public void removeUserFromCourse(User user){
        if(user.getClass().getTypeName().equals("Student")){
            Student student = (Student) user;
            student.removeCourse(this);
            studentsAndMarks.remove(student);
        } else if (user.getClass().getTypeName().equals("Teacher")) {
            Teacher teacher = (Teacher) user;
            teacher.removeCourse(this);
            this.teacher=null;
        }
    }
   public boolean hasTimeCohesion(Vector<Lesson> lessons1, Vector<Lesson> lessons2) {
        for (Lesson lesson1 : lessons1) {
            for (Lesson lesson2 : lessons2) {
                if (!lesson1.hasCohesion(lesson2)) {
                    return false;
                }
            }
        }
        return true;
    }
    public void viewMaterial(){
        for (String material : materials){
            int index = materials.indexOf(material)+1;
            System.out.println(index + " : " + material);
        }
    }
    public void addMaterial(String material){
        materials.add(material);
    }
    public void removeMaterial(int num){
        this.materials.remove(num);
    }
    public String toString(){
        return teacher.name + teacher.surname + this.title;
    }
    public void viewStudentsOnCourse(){
        CourseAdepter.viewObjectsInArray(studentsAndMarks.keySet().toArray(new Student[0]));
    }
    public Student getStudentFromCourse(int num){
        return CourseAdepter.getObjectFromArray(CourseAdepter.toArray(studentsAndMarks.keySet()),num);
    }
    public void viewLessons(){
        CourseAdepter.viewObjectsInArray(lessons.toArray(new Lesson[0]));
    }
    public Lesson getLessonFromCourse(int num){
        return CourseAdepter.getObjectFromArray(CourseAdepter.toArray(lessons),num);
    }
    public void addLesson(int hour,int minute,String Room,Enums.Day day,Enums.Type type,String room){
        Lesson lesson = new Lesson(hour,minute,type,day,room);
        if(!Intronet.checkLessonCohesion(lesson)){
            lessons.add(lesson);
        }
    }
    public void removeLesson(Lesson lesson){
        Intronet.lessons.remove(lesson);
        lessons.remove(lesson);
    }
}
