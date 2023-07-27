package Core;
import Users.Student;
import Users.Teacher;
import Users.User;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Vector;

public class Intronet {
    static Vector<Course> courses;
    public static Vector<User> users;
    static Vector<Request> requests;
    static Vector<News> news;
    public static int maxUserName = 0;
    private static final int idLength = 6;
    public Intronet(){
        courses = new Vector<>();
        users = new Vector<>();
    }
    public static String generateUserId(){

        String year= "" + (Calendar.YEAR - 2000);
        return year + "B" + ("0".repeat(idLength - ("" + users.size()).length()) + ("" + users.size()));
    }
    public static String generateCourseId() {
        String year= "" + (Calendar.YEAR - 2000);
        return (year) + "C" + ("0".repeat(idLength - ("" + courses.size()).length()) + ("" + courses.size()));
    }
    public static void addCourseToSystem(Course course){
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
    public static Course getCourseById(String id){
        for(Course course : courses){
            if(course.getId().equals(id)){
                return course;
            }
        }
        return null;
    }
    public static void addTeacherToCourse(Lesson lesson, Teacher teacher){
        if(teacher.getSchedule().isEmpty(lesson)){
            teacher.getSchedule().addLesson(lesson);
            lesson.teacher = teacher;
        }
    }
    public static void addStudentToCourse(Student student, Course course){
        Schedule studentSchedule = student.getSchedule();
        if(!(course.schedule.checkCohesion(studentSchedule))){
//            if(course.lessons.size()!=0){
//                for(Lesson lesson: course.lessons){
//                    studentSchedule.addLesson(lesson);
//                }
//            }
            course.studentMarks.put(student.getId(),new Mark());
            student.courses.add(course.getId());
        }
    }
    public static void addLessonToCourse(Course course,Lesson lesson){
        boolean isEmpty = true;
        for(Student student : course.StudentsOnCourse()){
            if(student.getSchedule().isEmpty(lesson)){
                isEmpty = false;
            }
        }
        if(isEmpty){
            lesson.name = course.name;
            for(Student student : course.StudentsOnCourse()){
                student.getSchedule().addLesson(lesson);
            }
        }
        else{
            System.out.println("Some user has cohesion in schedule!");
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
    public static int maxCourseId(){
        int maxLength = 0;
        for(Course course : courses){
            String id = course.getId();
            if(maxLength<id.length()){
                maxLength = id.length();
            }
        }
        return maxLength;
    }
}
