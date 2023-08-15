import Core.Course;
import Core.Intronet;
import Core.News;
import Enums.Degree;
import Enums.Faculty;
import Enums.Role;
import Frontend.MainGui;
import Users.Admin;
import Users.Manager;
import Users.Student;
import Users.Teacher;

import java.io.IOException;

public class SchedulePrint {
    public static void main(String[] args) throws IOException {

        if(Intronet.getInstance().users.size()==0){
            Manager manager = new Manager("login", "password", "John", "Doe", Role.MANAGER, Faculty.FIT);
            Student student1 = new Student("student1", "password1", "Alice", "Smith", Role.STUDENT, Faculty.FIT, Degree.BS);
            Student student2 = new Student("student2", "password2", "Bob", "Johnson", Role.STUDENT, Faculty.FEOGI, Degree.BS);
            Student student3 = new Student("student3", "password3", "Eve", "Williams", Role.STUDENT, Faculty.FIT, Degree.MS);
            Student student4 = new Student("student4", "password4", "Eldr", "Willianos", Role.STUDENT, Faculty.FIT, Degree.BS);
            Teacher teacher1 = new Teacher("teacher1_login", "teacher1_password", "Johnaa", "Doasde", Role.TEACHER, Faculty.FIT, Degree.PHD);
            Teacher teacher2 = new Teacher("1", "1", "Jane", "Smith", Role.TEACHER, Faculty.FEOGI, Degree.MS);
            Teacher teacher3 = new Teacher("teacher3_login", "teacher3_password", "Michael", "Johnson", Role.TEACHER, Faculty.ISE, Degree.PHD);
            Teacher teacher4 = new Teacher("teacher4_login", "teacher4_password", "Emily", "Brown", Role.TEACHER, Faculty.KMA, Degree.MS);
            // Первый объект Course
            Course course1 = new Course(Faculty.FIT, "Physics 101", "Introduction to Physics", 3, 50, "PHY101");
            Course course2 = new Course(Faculty.FEOGI, "English Literature", "Classic Literature Overview", 4, 40, "ENG202");
            Course course3 = new Course(Faculty.MCM, "Computer Programming", "Introduction to Programming", 4, 60, "COMP101");
            Course course4 = new Course(Faculty.KMA, "Painting Techniques", "Various Artistic Styles", 2, 30, "ART303");
            Course course5 = new Course(Faculty.FIT, "Marketing Fundamentals", "Principles of Marketing", 3, 50, "BUS201");

            Admin admin = new Admin("admin", "admin", "Richard", "Dudlas", Role.ADMIN, Faculty.FIT);

            Intronet.getInstance().addUserToSystem(manager);

            Intronet.getInstance().addUserToSystem(student1);
            Intronet.getInstance().addUserToSystem(student2);
            Intronet.getInstance().addUserToSystem(student3);
            Intronet.getInstance().addUserToSystem(student4);

            Intronet.getInstance().addUserToSystem(teacher1);
            Intronet.getInstance().addUserToSystem(teacher2);
            Intronet.getInstance().addUserToSystem(teacher3);
            Intronet.getInstance().addUserToSystem(teacher4);

            Intronet.getInstance().addCourseToSystem(course1);
            Intronet.getInstance().addCourseToSystem(course2);
            Intronet.getInstance().addCourseToSystem(course3);
            Intronet.getInstance().addCourseToSystem(course4);
            Intronet.getInstance().addCourseToSystem(course5);

            Intronet.getInstance().addUserToSystem(admin);

            News news1 = new News("New Exhibit Opening", "Join us for the grand opening of our new art exhibit this weekend!");
            News news2 = new News("Upcoming Workshop: Photography Basics", "Learn the fundamentals of photography in our upcoming workshop.");
            News news3 = new News("Important Policy Update", "Please be aware of the new policy changes coming into effect next month.");
            News news4 = new News("Community Cleanup Day", "Volunteers needed for the community cleanup event on Saturday.");
            News news5 = new News("Sports Tournament Results", "Congratulations to our team for their outstanding performance in the recent sports tournament!");
            News news6 = new News("Charity Fundraiser Success", "Thanks to your generous contributions, we raised $10,000 for the local charity.");

            Intronet.getInstance().news.add(news1);
            Intronet.getInstance().news.add(news2);
            Intronet.getInstance().news.add(news3);
            Intronet.getInstance().news.add(news4);
            Intronet.getInstance().news.add(news5);
            Intronet.getInstance().news.add(news6);
        }

        MainGui.menu();
    }

}
