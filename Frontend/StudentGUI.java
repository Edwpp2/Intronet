package Frontend;

import Core.Course;
import Core.CourseAdepter;
import Core.Intronet;
import Core.Message;
import Users.Student;
import Users.User;

import java.util.Scanner;

public class StudentGUI {
    Course course;
    public  StudentGUI(){};
    Scanner input = new Scanner(System.in);
    public void menu(Student student){
        int stage = 0;
        while (student!=null){
            System.out.println(stage);
            int command = 0;
            if(stage==0){
                System.out.println("Chose an option:");
                System.out.println("[1]View news;");
                System.out.println("[2]View schedule;");
                System.out.println("[3]View list of courses for registration;");
                System.out.println("[4]View list of current courses;");
                System.out.println("[5]View email;");
                System.out.println("[6]Write a message;");
                System.out.println("[7]Rate teachers;");
                System.out.println("[8]View transcript");
                System.out.println("[9]Exit.");
                command = input.nextInt();
                System.out.println(command);
                if(command==1){
                    System.out.println("Print news");
                }
                if(command==2){
                    SchduleDrawer.printSchedule(student.getSchedule());
                }
                if(command==3){
                    stage=1;
                }
                if(command==4){
                    stage=2;
                }
                if(command==5){
                    stage=4;
                }
                if(command==5){
                    stage=5;
                }
                if(command==7){
                    student=null;
                }
            }
            if(stage==1){
                SchduleDrawer.printCoursesForRegistration(student);
                System.out.println("Chose an option:");
                System.out.println("[1]Make a request for registration on course under number #;");
                System.out.println("[2]Back;");
                command = input.nextInt();
                if(command==1){
                    System.out.println("Enter number of the course");
                    int courseNum = input.nextInt();
                    Course course = (Course)CourseAdepter.getObjectFromArray(Intronet.courses.toArray(),courseNum);
                }
                if (command==2){
                    stage--;
                }
            }
            if(stage==2){
                SchduleDrawer.printInfoAboutStudentCourses(student);
                System.out.println("Chose an option:");
                System.out.println("[1]Choose a course under number #");
                System.out.println("[2]Back");
                command = input.nextInt();
                if(command==1){
                    System.out.println("Enter the number:");
                    stage=3;
                    course=null;
                }
                if(command==2){
                    stage=0;
                }
            }
            if(stage==3){
                if(course==null){
                    int courseNumber = input.nextInt();
                    if(courseNumber<=student.courses.size()){
                        course =Intronet.getCourseById((String)CourseAdepter.getObjectFromArray(student.courses.keySet().toArray(),courseNumber));
                    }
                    else {
                        System.out.println("You enter wrong number,try again!");
                    }
                }
                else {
                    System.out.println("Chose an option:");
                    System.out.println("[1]View info about teacher");
                    System.out.println("[2]View course files;");
                    System.out.println("[3]View course files;");
                    System.out.println("[4]Back");
                    command = input.nextInt();
                    if(command==1){
                        System.out.println("TO DO");
                    }
                    if(command==2){
                        SchduleDrawer.printMaterials(course);
                    }
                    if (command==3){
                        SchduleDrawer.printMarksForCurrentStudent(student,course,0,1);
                    }
                    if (command==4){
                        stage--;
                        course=null;
                    }
                }

            }
            if (stage==4){
                student.viewAllMessages();
                stage=0;
            }
            if(stage==5){
                System.out.println("Enter login of user!");
                String login = input.next();
                System.out.println("Write a text!");
                String text = input.next();
                Message message = new Message(Intronet.getUserByLogin(login),text);
                User user = Intronet.getUserByLogin(login);
                user.messages.add(message);
                stage--;
            }
        }
    }
}
