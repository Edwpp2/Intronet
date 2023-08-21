package Constructors;
import Core.Course;
import Core.InputVerification;
import Core.Lesson;
import Core.Logs;
import Enums.Day;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * This class is for lesson creation
 *
 * @author Eduardo
 */

public class LessonConstructor {

    /**
     * This is lesson creation function
     * @param course Course to which lesson will be added
     * @param input Buffer reader which get input data
     * @return Lesson which will be added to course
     * @throws IOException
     */
    public static Lesson lessonCreation(Course course, BufferedReader input) throws IOException {
        Day day = null;
        String room=null;
        int hour=0;

        while (day==null){
            System.out.println("Choose day:");
            System.out.println("[1]MONDAY;\n[2]TUESDAY;\n[3]WEDNESDAY;\n[4]THURSDAY;\n[5]FRIDAY;\n[6]SATURDAY;\n[7]SUNDAY;");
            int index = InputVerification.intValueCheck(input.readLine());
            if(index < 0 || index > 7){
                System.out.println("Wrong number!");
            }
            else {
                day = Day.values()[index-1];
            }
        }
        while (room==null){
            System.out.println("Enter room:");
            room = input.readLine();
        }
        while (hour==0){
            System.out.println("Enter hour:");
            hour = InputVerification.intValueCheck(input.readLine());
            if(hour < 0 || hour > 21){
                hour=0;
                System.out.println("WRONG NUMBER!");
            }
        }
        Lesson lesson = new Lesson(day,room,hour-9);
        Logs.AddToLog("Lesson at " +  day.name() + " " + room + " " + hour+":00 was added to " + course.name);
        if(course.teacher!=null){
            lesson.teacher=course.teacher;
        }
        return lesson;
    }
}
