package Core;
import Users.Student;
import Users.Teacher;
import Users.User;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Vector;

public class Intronet {
    static Vector<Course> courses;
    public static HashSet<User> users;
    static Vector<Request> requests;
    static Vector<News> news;
    private static final int idLength = 6;

    public String generateUserId(){

        String year= "" + (Calendar.YEAR - 2000);
        return year + "B" + ("0".repeat(idLength - ("" + users.size()).length()) + ("" + users.size()));
    }
    public String generateCourseId() {
        String year= "" + (Calendar.YEAR - 2000);
        return (year) + "C" + ("0".repeat(idLength - ("" + courses.size()).length()) + ("" + courses.size()));
    }
    public void addCourseToSystem(Course course){
        courses.add(course);
        course.setId(this.generateCourseId());

    }
    public void addUserToSystem(User user){
        users.add(user);
        user.setId(this.generateUserId());
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
    public void addTeacherToCourse(Lesson lesson, Teacher teacher){
        if(teacher.getSchedule().isEmpty(lesson)){
            teacher.getSchedule().addLesson(lesson);
            lesson.teacher = teacher;
        }
    }
    public void addStudentToCourse(Student student, Course course){
        Schedule studentSchedule = student.getSchedule();
        if(!(course.schedule.checkCohesion(studentSchedule))){
            for(Lesson lesson: course.lessons){
                studentSchedule.addLesson(lesson);
            }
        }
    }
    public void addLessonToCourse(Course course,Lesson lesson){
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
}
