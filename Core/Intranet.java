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
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Intranet implements Serializable {
    private static Intranet intranet;
    public Vector<Course> courses;
    public Vector<User> users;
    public Vector<Message> messages;
    public Vector<Request> requests;
    public Vector<News> news;
    public int maxUserName = 0;
    public int maxCourseName  = 0;
    private static final int idLength = 6;

    static {
        intranet = null;
        String filename = "intranet.txt";
        Path filePath = Paths.get(filename);
        if (Files.exists(filePath)) {
            try {
                FileInputStream fileIn = new FileInputStream(filename);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                intranet = (Intranet) in.readObject();
                in.close();
                fileIn.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        else {
            intranet = new Intranet();
        }
    }
    public Intranet(){
        courses = new Vector<>();
        users = new Vector<>();
        news = new Vector<>();
        requests = new Vector<>();
        messages = new Vector<>();
    }
    public User login(BufferedReader input) throws IOException {
        System.out.println("Enter login");
        String login = input.readLine();
        System.out.println("Enter password");
        String password = input.readLine();
        for (User user : users){
            if(user.login.equals(login) && user.password.equals(password)){
                if(!user.isBlocked()){
                    Logs.AddToLog("User " + user.name + " " + user.surname +  " login to system");
                    return user;
                }
                else {
                    System.out.println("This user blocked in system!");
                    return null;
                }

            }
        }
        System.out.println("No such user!");
        return null;
    }
    public String generateUserId(){
        String year= "" + (Year.now().getValue()-2000);
        return year + "B" + ("0".repeat(idLength - ("" + users.size()).length()) + ("" + users.size()));
    }
    public  String generateCourseId() {
        String year= "" + (Year.now().getValue()-2000);
        return (year) + "C" + ("0".repeat(idLength - ("" + courses.size()).length()) + ("" + courses.size()));
    }
    public  void addCourseToSystem(Course course){
        if(course.name.length()>maxCourseName){
            maxCourseName = course.name.length();
        }
        course.setId(this.generateCourseId());
        courses.add(course);
    }
    public void addUserToSystem(User user){
        String userName = user.name + " " + user.surname;
        if(maxUserName < userName.length()){
            maxUserName = userName.length();
        }
        user.setId(this.generateUserId());
        users.add(user);
    }
    public User getUserById(String id){
        for (User user : intranet.users){
            if(user.getId().equals(id)){
                return user;
            }
        }
        return null;
    }
    public User getUserByLogin(String login){
        for (User user : users){
            if(user.login.equals(login)){
                return user;
            }
        }
        return null;
    }
    public Course getCourseById(String id){
        for(Course course : courses){
            if(course.getId().equals(id)){
                return course;
            }
        }
        return null;
    }
    public void addTeacherToCourse(Course course, Teacher teacher) {
        Schedule teacherSchedule = teacher.getSchedule();
        if (!course.schedule.checkCohesion(teacherSchedule)) {
            course.teacher = teacher;
            teacher.courses.add(course.getId());
            updateLessonNameForCourse(course);
            Logs.AddToLog("Teacher " + teacher.name + " " + teacher.surname + " was added to " + course.name);
        }
        else {
            System.out.println("Teacher has time cohesion!");
        }
    }
    public void updateLessonNameForCourse(Course course){
        for(Lesson lesson:course.lessons){
            lesson.teacher=course.teacher;
            course.schedule.updateLessonName(lesson);
            for (String id : course.studentMarks.keySet()) {
                Student student = (Student) this.getUserById(id);
                student.schedule.updateLessonName(lesson);
            }
        }
    }

    public void dropTeacherFromCourse(Course course, Teacher teacher){
        {
            course.teacher=null;
            updateLessonNameForCourse(course);
            teacher.courses.remove(course.getId());
            Logs.AddToLog("Teacher " + teacher.name + " " + teacher.surname +  " was dropped from" + course.name);
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
            course.studentMarks.put(student.getId(),new Mark());
            student.courses.put(course.getId(),new Mark());
            student.credits=student.credits-course.credits;
            System.out.println(course.teacherRating.get(student.getId()));
            Logs.AddToLog("Student " + student.name + student.surname + " was added to" + course.name);
        }
    }
    public static  void  dropStudentFromCourse(Student student,Course course){
        Schedule studentSchedule = student.getSchedule();
        studentSchedule.cleanSchedule(course.schedule);
        student.credits=student.credits+course.credits;
        student.courses.remove(course.getId());
        course.studentMarks.remove(student.getId());
        course.teacherRating.remove(student.getId());
        Logs.AddToLog("Student " + student.name + student.surname + " was dropped from" + course.name);
    }
    public boolean checkCohesionForStudentsOnCourse(Course course,Lesson lesson){
        if(course.studentMarks.size()>0){
            for(String id : course.studentMarks.keySet()){
                Student student =(Student) this.getUserById(id);
                if(!student.getSchedule().isEmpty(lesson.hour, lesson.day.ordinal())){
                    return false;
                }
            }
        }
        return true;
    }
    public void addLessonToCourse(Course course,Lesson lesson){
        boolean isEmpty = true;
        boolean teacherHasCohesion = course.teacher!=null && !course.teacher.getSchedule().isEmpty(lesson.hour, lesson.day.ordinal());
        if(course.studentMarks.size()>0){
            isEmpty = checkCohesionForStudentsOnCourse(course,lesson) && !teacherHasCohesion;
        }
        if (isEmpty) {
            lesson.name = course.name;
            if (course.teacher != null) {
                course.teacher.getSchedule().addLesson(lesson);
            }
            for (Student student : course.StudentsOnCourse()) {
                student.getSchedule().addLesson(lesson);
            }
            course.schedule.addLesson(lesson);
            course.lessons.add(lesson);
            Logs.AddToLog("Lesson was added to " + course.name);
        }
        else {
            System.out.println("Some user has cohesion in schedule!");
        }
    }
    public static void dropLessonFromStudentsSchedule(Course course, int hour, Day day){
        for(Student student : course.StudentsOnCourse()){
            Schedule studentSchedule = student.getSchedule();
            if(!studentSchedule.isEmpty(hour,day.ordinal())){
                studentSchedule.dropLesson(hour,day.ordinal());
            }
        }

    }
    public static void dropLessonFromCourse(Course course, int hour, Day day){
        if(!course.schedule.isEmpty(hour,day.ordinal())){
            dropLessonFromStudentsSchedule(course,hour,day);
            if(course.teacher!=null){
                course.teacher.getSchedule().dropLesson(hour,day.ordinal());
            }
            course.schedule.dropLesson(hour,day.ordinal());
            Logs.AddToLog("Lesson was dropped from" + course.name);
        }
        else {
            System.out.println("No such lesson!");
        }
    }
    public List<Teacher> enableTeachers(Course course) {
        List<Teacher> teachers = new ArrayList<>();
        for (User user : users) {
            if (user.role == Role.TEACHER && user.faculty == course.faculty && course.teacher != user) {
                teachers.add((Teacher) user);
            }
        }
        return teachers;
    }
    public int maxCourseName(){
        int maxLength = 0;
        for(Course course : courses){
            if(maxLength<course.name.length()){
                maxLength = course.name.length();
            }
        }
        return maxLength;
    }
    public void printNews(){
        int i = 0;
        for(News part : news){
            SchduleDrawer.printNews(i,part);
            i++;
        }
    }
    public List<Request> getFacultyRequest(Manager manager){
        List<Request> requests = new ArrayList<>();
        for (Request request: intranet.requests){
            if(request.faculty==manager.faculty){
                requests.add(request);
            }
        }
        return requests;
    }
    public void displayFacultyRequests(Manager manager){
        List<Request> requests = getFacultyRequest(manager);
        for (int i = 0; i < requests.size();i++){
            System.out.println("[" + (i+1) + "]" + " " + requests.get(i).toString());
        }
    }
    public List<String> availableCourses(Student student){
        List<String> availableCourses = new ArrayList<>();
        for(Course course : courses){
            if(!student.courses.containsKey(course.getId())){
                availableCourses.add(course.getId());
            }
        }
        return availableCourses;
    }
    public static void serializeIntranet(String filename) {
        try (FileOutputStream fileOut = new FileOutputStream(filename);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)){

            out.writeObject(getInstance());
            out.close();
            fileOut.close();

            System.out.println("Intranet serialized to " + filename);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Intranet getInstance() {
        if (intranet == null) {
            intranet = new Intranet();
        }
        return intranet;
    }
}
