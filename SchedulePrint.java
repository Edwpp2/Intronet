import Core.Lesson;
import Core.Schedule;
import Enums.Day;
import Frontend.SchduleDrawer;

public class SchedulePrint {
    public static void main(String[] args) {
        String[] header = {"Time","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
        String[] time = {"9:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00","20:00","21:00"};
        Schedule schedule = new Schedule();

        schedule.addLesson(new Lesson("POH",Day.MONDAY, "2", 14-9));
        schedule.addLesson(new Lesson("VASHE POHUY",Day.WEDNESDAY, "3", 13-9));
        schedule.addLesson(new Lesson("POEBATY",Day.FRIDAY, "4", 9-9));
        schedule.addLesson(new Lesson("URAAAAAAAAAAAAAAAAAAAAAAAAA",Day.MONDAY, "6", 12-9));
        schedule.addLesson(new Lesson("URAAAAAAAAAAAAAAAAAAAAAAAAB",Day.MONDAY, "6", 12-9));
        SchduleDrawer.cellFill(schedule);


    }

}
