import Enums.Type;
import Enums.Day;
import Enums.Department;
import Enums.Role;

public class TEST {
    public static void main(String[] args) {
        Lesson lesson1 = new Lesson(9, 30, Type.OFFLINE, Day.MONDAY, "A101");
        Lesson lesson2 = new Lesson(10, 45, Type.OFFLINE, Day.MONDAY, "B202");
        Lesson lesson3 = new Lesson(13, 0, Type.OFFLINE, Day.TUESDAY, "C303");
        Lesson lesson4 = new Lesson(14, 15,Type.OFFLINE, Day.TUESDAY, "D404");
        Lesson lesson5 = new Lesson(11, 30, Type.OFFLINE, Day.WEDNESDAY, "E505");
        Lesson lesson6 = new Lesson(13, 45, Type.OFFLINE, Day.WEDNESDAY, "F606");
        Lesson lesson7 = new Lesson(8, 0, Type.OFFLINE, Day.THURSDAY, "G707");
        Lesson lesson8 = new Lesson(9, 15, Type.OFFLINE, Day.THURSDAY, "H808");
        Lesson lesson9 = new Lesson(12, 30, Type.OFFLINE, Day.FRIDAY, "I909");
        Lesson lesson10 = new Lesson(15, 0, Type.OFFLINE, Day.FRIDAY, "J1010");

        Student student1 = new Student("student1", "password1", "John", "Doe", "123456", Role.STUDENT, Department.FIT, 2);
        Student student2 = new Student("student2", "password2", "Alice", "Smith", "234567", Role.STUDENT, Department.FIT, 3);
        Student student3 = new Student("student3", "password3", "Michael", "Johnson", "345678", Role.STUDENT,Department.FIT, 1);
        Student student4 = new Student("student4", "password4", "Emily", "Davis", "456789", Role.STUDENT, Department.FIT, 4);
        Student student5 = new Student("student5", "password5", "David", "Wilson", "567890", Role.STUDENT, Department.FIT, 2);

        Teacher teacher1 = new Teacher("teacher1", "password1", "John", "Doe", "T123456", Role.TEACHER, Department.FIT);
        Teacher teacher2 = new Teacher("teacher2", "password2", "Alice", "Smith", "T234567", Role.TEACHER, Department.FIT);
        Teacher teacher3 = new Teacher("teacher3", "password3", "Michael", "Johnson", "T345678", Role.TEACHER, Department.FIT);
        Teacher teacher4 = new Teacher("teacher4", "password4", "Emily", "Davis", "T456789", Role.TEACHER, Department.FIT);
        Teacher teacher5 = new Teacher("teacher5", "password5", "David", "Wilson", "T567890", Role.TEACHER, Department.FIT);

        Manager manager1 = new Manager("manager1", "password1", "John", "Doe", "M123456", Role.MANAGER, Department.FIT);
        Manager manager2 = new Manager("manager2", "password2", "Alice", "Smith", "M234567", Role.MANAGER, Department.FIT);

        Course course1 = new Course(Department.FIT, "Introduction to Programming", "Learn the basics of programming", 3, 50);
        Course course2 = new Course(Department.FIT, "Quantum Mechanics", "Study the principles of quantum mechanics", 4, 30);
        Course course3 = new Course(Department.FIT, "Cell Biology", "Explore the structure and function of cells", 3, 40);
        Course course4 = new Course(Department.FIT, "Electrical Circuits", "Learn about electrical circuit analysis", 3, 50);
        Course course5 = new Course(Department.FIT, "Linear Algebra", "Study vector spaces and linear transformations", 3, 60);





    }
}
