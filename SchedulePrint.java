import Core.Course;
import Core.Intronet;
import Core.Lesson;
import Core.Schedule;
import Enums.Day;
import Enums.Degree;
import Enums.Faculty;
import Enums.Role;
import Frontend.SchduleDrawer;
import Users.Student;

public class SchedulePrint {
    public static void main(String[] args) {
//        String[] header = {"Time","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
//        String[] time = {"9:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00","20:00","21:00"};
//        Schedule schedule = new Schedule();
//
//        schedule.addLesson(new Lesson("POH",Day.MONDAY, "2", 14-9));
//        schedule.addLesson(new Lesson("VASHE POHUY",Day.TUESDAY, "3", 13-9));
//        schedule.addLesson(new Lesson("POEBATY",Day.WEDNESDAY, "4", 9-9));
//        schedule.addLesson(new Lesson("URAAAAAAAAAAAAAAAAAAAAAAAAA",Day.THURSDAY, "6", 12-9));
//        schedule.addLesson(new Lesson("URAAAAAAAAAAAAAAAAAAAAAAAAB",Day.FRIDAY, "6", 12-9));
//        schedule.addLesson(new Lesson("URAAAAAAA",Day.SATURDAY, "6", 21-9));
//        schedule.addLesson(new Lesson("URAAAAAAAAAB",Day.MONDAY, "6", 17-9));
//        SchduleDrawer.printSchedule(schedule);
        Intronet intronet = new Intronet();
        Student student = new Student("student01", "password123", "John", "Doe", Role.STUDENT, Faculty.BS, Degree.BS, 2);
        Course course = new Course(Faculty.BS, "Programming 101", "Introduction to Programming", 3, 50);
        Intronet.addCourseToSystem(course);
        Intronet.addStudentToCourse(student,course);
        Intronet.addUserToSystem(student);
        SchduleDrawer.printCoursesForStudent(student);
        SchduleDrawer.printUsersForSystem();

    }

}
