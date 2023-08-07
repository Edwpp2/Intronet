package Constructors;
import Core.Course;
import Core.Lesson;
import Enums.Day;
import java.util.Scanner;
public class LessonConstructor {
    public static Lesson lessonCreation(){
        Day day = null;
        String room=null;
        int hour=0;
        Scanner input = new Scanner(System.in);
        while (day==null){
            System.out.println("Выберите день проведения урока:");
            System.out.println("[1]MONDAY;\n[2]TUESDAY;\n[3]WEDNESDAY;\n[4]THURSDAY;\n[5]FRIDAY;\n[6]SATURDAY;\n[7]SUNDAY;");
            int index = input.nextInt();
            if(index < 0 || index > 7){
                System.out.println("Вы ввели не корректный номер!");
            }
            else {
                day = Day.values()[index-1];
            }
        }
        while (room==null){
            System.out.println("Введите назмвание комнаты:");
            room = input.next();
        }
        while (hour==0){
            System.out.println("Введите время проведения курса:");
            hour = input.nextInt();
        }
        return new Lesson(day,room,hour);
    }
}
