package Frontend;

import Constructors.LessonConstructor;
import Core.Course;
import Core.Intronet;
import Core.Lesson;
import Users.Manager;

import java.util.Scanner;

public class CourseGUI {
    public static void menu(Manager manager, Course course,Lesson lesson, Scanner input, boolean start) {
        int command = 0;
        int internalStage =0;
        while (start){
            if(internalStage==0){
                System.out.println("Выберите действие");
                System.out.println("[1]Выбрать курс");
                System.out.println("[2]Back");
                if(command==1){
                    internalStage++;
                }
                if(command==2){
                    internalStage=0;
                    start=false;
                }
            }
            if(internalStage==1){
                SchduleDrawer.printAllCoursesInSystem();
                System.out.println("Выберите номер курса");
                int i = input.nextInt();
                course = (Course) Intronet.courses.toArray()[i];
                if(course!=null){
                    internalStage++;
                }
                else {
                    System.out.println("Ошибка!");
                    internalStage--;
                }
            }
            if(internalStage==2){
                System.out.println("Выберите действие");
                System.out.println("[1]Set teacher");
                System.out.println("[2]Menage lessons");
                System.out.println("[3]Back");
                command=input.nextInt();
                if(command==1){
//                        System.out.println("AAAAAAAAAAAAAAAAAA");AAAAAAAAAAAAAAAAAAAA
                }
                if(command==2){
                    SchduleDrawer.printListOfLesson(course);
                    System.out.println("Выберите номер урока");
                    int index = input.nextInt();
                    lesson = (Lesson) course.lessons.toArray()[index-1];
                    if(lesson!=null){
                        internalStage++;
                    }
                }
                if(command==3){
                    internalStage--;
                    course=null;
                }
            }
            if(internalStage==3){
                System.out.println("Выберите действие");
                System.out.println("[1]Drop lesson");
                System.out.println("[2]Add lesson");
                System.out.println("[3]Back");
                command = input.nextInt();
                if(command==1){
                    Intronet.dropLessonToCourse(course,lesson);
                }
                if(command==2){
                    Intronet.addLessonToCourse(course, LessonConstructor.lessonCreation());
                }
                if(command==3){
                    internalStage--;
                    lesson=null;
                }
            }

        }
    }
}
