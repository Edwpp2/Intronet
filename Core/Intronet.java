package Core;
import Users.Manager;
import Users.Student;
import Users.Teacher;
import Users.User;
import java.time.Year;
import java.util.HashSet;
import java.util.Vector;

public class Intronet {
    public static Vector<Course> courses;
    public static Vector<User> users;
    public static Vector<Message> messages;
    public static Vector<Request> requests;
    public static Vector<News> news;
    public static int maxUserName = 0;
    public static int maxCourseName  = 0;
    private static final int idLength = 6;

    public HashSet<String> logs;
    public Intronet(){
        courses = new Vector<>();
        users = new Vector<>();
        news = new Vector<>();
        requests = new Vector<>();
        messages = new Vector<>();
    }
    public static String generateUserId(){
        String year= "" + (Year.now().getValue()-2000);;
        return year + "B" + ("0".repeat(idLength - ("" + users.size()).length()) + ("" + users.size()));
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
        for(String id : course.studentMarks.keySet()){
            Student student =(Student) Intronet.getUserById(id);
            if(student.getSchedule().isEmpty(lesson)){
                isEmpty = false;
            }
        }
        if(course.teacher.getSchedule().isEmpty(lesson)){
            isEmpty = false;
        }
        if(isEmpty){
            lesson.name = course.name;
            course.teacher.getSchedule().addLesson(lesson);
            for(Student student : course.StudentsOnCourse()){
                student.getSchedule().addLesson(lesson);
            }
        }
        else{
            System.out.println("Some user has cohesion in schedule!");
        }
    }
    public static void dropLessonToCourse(Course course,Lesson lesson){
        for(Student student : course.StudentsOnCourse()){
            if(!student.getSchedule().isEmpty(lesson)){
                student.getSchedule().dropLesson(lesson);
            }
        }
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
        for(News part : news){
            System.out.println(part.toString());
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
}
