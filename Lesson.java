import Enums.Day;
import Enums.Department;
import Enums.Role;
import Enums.Type;

import java.time.LocalTime;

public class Lesson implements Comparable<Lesson> {
    public LocalTime time;
    Type type;
    Day day;
    String room;
    String name;
    Teacher teacher;
    public Lesson(int hour,int min, Type type, Day day, String room) {
        this.type = type;
        this.day = day;
        this.room = room;
        this.time = LocalTime.of(hour, min);
        this.teacher = new Teacher("teacher5", "password5", "David", "Wilson", "T567890", Role.TEACHER, Department.FIT);
    }
    public boolean hasCohesion(Lesson lesson2){
        return !this.time.isAfter(lesson2.time)||!this.time.isBefore(lesson2.time);
    }
    @Override
    public int compareTo(Lesson lesson) {
        if(lesson.time.getHour()==this.time.getHour()){
            return lesson.time.getMinute()>this.time.getMinute() ? 1:-1;
        }
        else return Integer.compare(this.time.getHour(), lesson.time.getHour());
    }
    public String toString(){
        return time.toString() + room + type;
    }
    public String toStringForScheduel(){
        String name = "OOOOPPPPPPP";
        String format =
                            " ____________________________\n" +
                            "|                            |\n" +
                            "|%-28s|\n" +
                            "|%-28s|\n" +
                            "|%-28s|\n" +
                            "|____________________________|\n";
        return String.format(format,centerText(name),centerText(teacher.toString()),centerText("(" + room + ")"));
    }
    private String centerText(String text) {
        int totalWidth = 28;
        int leftPadding = (totalWidth - text.length()) / 2;
        int rightPadding = totalWidth - text.length() - leftPadding;
        return " ".repeat(leftPadding) + text + " ".repeat(rightPadding);
    }
}
