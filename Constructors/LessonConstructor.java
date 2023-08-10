package Constructors;
import Core.Course;
import Core.Lesson;
import Enums.Day;
import java.util.Scanner;
public class LessonConstructor {
    public static Lesson lessonCreation(Course course){
        Day day = null;
        String room=null;
        int hour=0;
        Scanner input = new Scanner(System.in);
        while (day==null){
            System.out.println("Choose day:");
            System.out.println("[1]MONDAY;\n[2]TUESDAY;\n[3]WEDNESDAY;\n[4]THURSDAY;\n[5]FRIDAY;\n[6]SATURDAY;\n[7]SUNDAY;");
            int index = input.nextInt();
            if(index < 0 || index > 7){
                System.out.println("Wrong number!");
            }
            else {
                day = Day.values()[index-1];
            }
        }
        while (room==null){
            System.out.println("Enter room:");
            room = input.next();
        }
        while (hour==0){
            System.out.println("Enter hour:");
            hour = input.nextInt();
            if(hour < 0 || hour > 21){
                hour=0;
                System.out.println("WRONG NUMBER!");
            }
        }
        Lesson lesson = new Lesson(day,room,hour-9);
        if(course.teacher!=null){
            lesson.teacher=course.teacher;
        }
        return lesson;
    }
}
