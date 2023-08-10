package Core;
import Enums.Day;
import Enums.Role;
import Frontend.SchduleDrawer;
import Users.Manager;
import Users.Student;
import Users.Teacher;
import Users.User;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Year;
import java.util.HashSet;
import java.util.Vector;

public class Intronet implements Serializable {
    private static Intronet intronet;
    public static Vector<Course> courses;
    public static Vector<User> users;
    public Vector<Message> messages;
    public static Vector<Request> requests;
    public static Vector<News> news;
    public static int maxUserName = 0;
    public static int maxCourseName  = 0;
    private static final int idLength = 6;

    public HashSet<String> logs;

    static {
        intronet = null;
        String filename = "intronet.txt";
        Path filePath = Paths.get(filename);
        if (Files.exists(filePath)) {
            try {
                FileInputStream fileIn = new FileInputStream(filename);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                intronet = (Intronet) in.readObject();
                in.close();
                fileIn.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        else {
            intronet = new Intronet();
        }
    }
    private Intronet(){
        courses = new Vector<>();
        users = new Vector<>();
        news = new Vector<>();
        requests = new Vector<>();
        messages = new Vector<>();
    }
    public static User login(String login,String password){
        for (User user : users){
            if(user.login.equals(login) && user.password.equals(password)){
                return user;
            }
        }
        return null;
    }
    public static String generateUserId(){
        String year= "" + (Year.now().getValue()-2000);;
        return year + "B" + ("0".repeat(idLength - ("" + users.size()).length()) + ("" + users.size()));
    }
    public void hello(){
        System.out.println("Hello");
    }
    public static String generateCourseId() {
        String year= "" + (Year.now().getValue()-2000);
        return (year) + "C" + ("0".repeat(idLength - ("" + courses.size()).length()) + ("" + courses.size()));
    }
    public static void addCourseToSystem(Course course){
        if(course.name.length()>maxCourseName){
            maxCourseName = course.name.length();
        }
        course.setId(Intronet.generateCourseId());
        courses.add(course);
    }
    public static void addUserToSystem(User user){
        String userName = user.name + " " + user.surname;
        if(maxUserName < userName.length()){
            maxUserName = userName.length();
        }
        user.setId(Intronet.generateUserId());
        users.add(user);
    }
    public static User getUserById(String id){
        for (User user : users){
            if(user.getId().equals(id)){
                return user;
            }
        }
        return null;
    }
    public static User getUserByLogin(String login){
        for (User user : users){
            if(user.login.equals(login)){
                return user;
            }
        }
        return null;
    }
    public static Course getCourseById(String id){
        for(Course course : courses){
            if(course.getId().equals(id)){
                return course;
            }
        }
        return null;
    }
    public static void addTeacherToCourse(Course course, Teacher teacher){
        Schedule teacherSchedule = teacher.getSchedule();
        if(!(course.schedule.checkCohesion(teacherSchedule))){
            if(course.lessons.size()!=0){
                for(Lesson lesson: course.lessons){
                    teacherSchedule.addLesson(lesson);
                }
            }
            course.teacher=teacher;
            teacher.courses.add(course.getId());
        }
    }
    public static void dropTeacherFromCourse(Course course, Teacher teacher){
        {
            for(Lesson lesson:course.lessons){
                lesson.teacher=null;
                course.schedule.updateLessonName(lesson);
            }
            course.teacher=null;
            teacher.courses.remove(course.getId());
        }
    }
    public static void addStudentToCourse(Student student, Course course){
        Schedule studentSchedule = student.getSchedule();
        if(!(course.schedule.checkCohesion(studentSchedule))){
            if(course.lessons.size()!=0){
                for(Lesson lesson: course.lessons){
                    studentSchedule.addLesson(lesson);
                }
            }
            Mark mark = new Mark();
            course.studentMarks.put(student.getId(),mark);
            student.courses.put(course.getId(),mark);
            student.credits=student.credits-course.credits;
        }
    }
    public static  void  dropStudentFromCourse(Student student,Course course){
        Schedule studentSchedule = student.getSchedule();
        studentSchedule.cleanSchedule(course.schedule);
        student.credits=student.credits+course.credits;
    }
    public static void addLessonToCourse(Course course,Lesson lesson){
        boolean isEmpty = true;
        if(course.studentMarks.size()>0){
            for(String id : course.studentMarks.keySet()){
                Student student =(Student) Intronet.getUserById(id);
                if(!student.getSchedule().isEmpty(lesson.hour, lesson.day.ordinal())){
                    isEmpty = false;
                }
            }
        }
        if(course.teacher!=null){
            if(!course.teacher.getSchedule().isEmpty(lesson.hour, lesson.day.ordinal())){
                isEmpty = false;
                System.out.println("Teacher not empty");
            }
        }
        if(isEmpty){
            lesson.name = course.name;
            if(course.teacher!=null){
                course.teacher.getSchedule().addLesson(lesson);
            }
            for(Student student : course.StudentsOnCourse()){
                student.getSchedule().addLesson(lesson);
            }
            course.schedule.addLesson(lesson);
            course.lessons.add(lesson);
        }
        else{
            System.out.println("Some user has cohesion in schedule!");
        }
    }
    public static void dropLessonFromCourse(Course course, int hour, Day day){
        for(Student student : course.StudentsOnCourse()){
            if(!student.getSchedule().isEmpty(hour,day.ordinal())){
                student.getSchedule().dropLesson(hour,day.ordinal());
            }
        }
        if(course.teacher!=null){
            course.teacher.getSchedule().dropLesson(hour,day.ordinal());
        }
        course.schedule.dropLesson(hour,day.ordinal());
    }
    public static Teacher[] enableTeachers(Course course){
        int arraySize = 0;
        for(User user : users){
            if(user.role== Role.TEACHER && user.faculty == course.faculty){
                arraySize++;
            }
        }
        Teacher[] teachers = new Teacher[arraySize];
        for(User user : users){
            if(user.role== Role.TEACHER && user.faculty == course.faculty){
                teachers[arraySize-1] = (Teacher) user;
                arraySize--;
            }
        }
        return teachers;
    }
    public static int maxCourseName(){
        int maxLength = 0;
        for(Course course : courses){
            String name = course.name;
            if(maxLength<name.length()){
                maxLength = name.length();
            }
        }
        return maxLength;
    }
    public static void printNews(){
        int i = 0;
        for(News part : news){
            SchduleDrawer.printNews(i,part);
            i++;
        }
    }
    public static Request[] getFacultyRequest(Manager manager){
        int facultySatisfyCount = 0;
        int i = 0;
        for (Request requests1:Intronet.requests){
            if(requests1.faculty==manager.faculty){
                facultySatisfyCount++;
            }
        }
        Request[] requests = new Request[facultySatisfyCount];
        for (Request requests1:Intronet.requests){
            if(requests1.faculty==manager.faculty){
                requests[i]=requests1;
                i++;
            }
        }
        return requests;
    }
    public static void displayFacultyRequests(Manager manager){
        Request[] requests = getFacultyRequest(manager);
        for (int i = 0; i < requests.length;i++){
            System.out.println("[" + i + "]" + " " + requests[i].toString());
        }
    }
    public static void serializeIntronet(String filename) {
        try (FileOutputStream fileOut = new FileOutputStream(filename);
             ObjectOutputStream out = new ObjectOutputStream(fileOut);){

            out.writeObject(getInstance());
            out.close();
            fileOut.close();

            System.out.println("Intronet serialized to " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Intronet getInstance() {
        if (intronet == null) {
            intronet= new Intronet();
        }
        return intronet;
    }
}
