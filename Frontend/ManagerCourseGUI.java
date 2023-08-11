package Frontend;

import Constructors.CourseConstructor;
import Constructors.LessonConstructor;
import Core.Course;
import Core.InputVerificator;
import Core.Intronet;
import Core.Lesson;
import Enums.Day;
import Users.Teacher;
import java.io.BufferedReader;
import java.io.IOException;
public class ManagerCourseGUI {

    public static void menu(BufferedReader input) throws IOException {
        int command;
        Course course=null;
        int internalStage = 0;
        boolean start = true;
        while (start) {
            if (internalStage == 0) {
                System.out.println("Choose an option");
                System.out.println("[1]Choose course");
                System.out.println("[2]Create course");
                System.out.println("[3]Back");
                command = InputVerificator.intValueCheck(input.readLine());
                if (command == 1) {
                    internalStage++;
                } else if (command == 2) {
                    internalStage = 4;
                } else if (command == 3) {
                    start = false;
                } else {
                    System.out.println("WRONG NUMBER!");
                }
            }
            if (internalStage == 1) {
                SchduleDrawer.printAllCoursesInSystem();
                System.out.println("Enter course number");
                int courseNumber = InputVerificator.intValueCheck(input.readLine());
                if (courseNumber < 1 || courseNumber > Intronet.getInstance().courses.toArray().length) {
                    System.out.println("Wrong number");
                    internalStage--;
                } else {
                    course = (Course) Intronet.getInstance().courses.toArray()[courseNumber - 1];
                    internalStage++;
                }
            }
            if (internalStage == 2) {
                System.out.println("Choose an option");
                System.out.println("[1]Set teacher");
                System.out.println("[2]Menage lessons");
                System.out.println("[3]Back");
                command = InputVerificator.intValueCheck(input.readLine());
                if (command == 1) {
                    internalStage = 5;
                } else if (command == 2) {
                    internalStage++;
                } else if (command == 3) {
                    internalStage -= 2;
                    course = null;
                } else {
                    System.out.println("WRONG NUMBER!");
                }
            }
            if (internalStage == 3) {
                SchduleDrawer.printSchedule(course.schedule);
                System.out.println("Choose an option");
                System.out.println("[1]Drop lesson");
                System.out.println("[2]Add lesson");
                System.out.println("[3]Back");
                command = InputVerificator.intValueCheck(input.readLine());
                if (command == 1) {
                    internalStage = 6;
                } else if (command == 2) {
                    Intronet.getInstance().addLessonToCourse(course, LessonConstructor.lessonCreation(course,input));
                } else if (command == 3) {
                    internalStage--;
                } else {
                    System.out.println("WRONG NUMBER!");
                }
            }
            if (internalStage == 4) {
                CourseConstructor.courseCreation(input);
                internalStage = 0;
            }
            if (internalStage == 5) {
                SchduleDrawer.printAvalibleTeachers(course);
                System.out.println("Choose an option");
                System.out.println("[1]Chose a teacher");
                System.out.println("[2]Back");
                command = InputVerificator.intValueCheck(input.readLine());
                if (command == 1) {
                    System.out.println("Enter number of teacher");
                    int teacherNumber = InputVerificator.intValueCheck(input.readLine());
                    if (teacherNumber < 0 || teacherNumber > Intronet.getInstance().enableTeachers(course).length) {
                        System.out.println("WRONG NUMBER!");
                    } else {
                        Teacher teacher = Intronet.getInstance().enableTeachers(course)[teacherNumber - 1];
                        Intronet.getInstance().addTeacherToCourse(course, teacher);
                    }
                } else if (command == 2) {
                    internalStage = 1;
                } else {
                    System.out.println("WRONG NUMBER!");
                }
            }
            if (internalStage == 6) {
                System.out.println("Enter hour:");
                int hour = InputVerificator.intValueCheck(input.readLine());
                if (hour < 8 || hour > 21) {
                    System.out.println("WRONG TIME!");
                } else {
                    System.out.println("Choose day from list");
                    System.out.println("[1]MONDAY;\n[2]TUESDAY;\n[3]WEDNESDAY;\n[4]THURSDAY;\n[5]FRIDAY;\n[6]SATURDAY;\n[7]SUNDAY;");
                    int index = InputVerificator.intValueCheck(input.readLine());

                    if (index < 1 || index > 7) {
                        System.out.println("Wrong number!");
                    } else {
                        Day day = Day.values()[index - 1];
                        for (Lesson lesson1 : course.lessons) {
                            if (lesson1.day == day && lesson1.hour == hour - 9) {
                                Intronet.dropLessonFromCourse(course, hour - 9, day);
                            }
                        }
                    }
                }
                internalStage = 2;
            }

        }

    }
}

