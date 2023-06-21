import Enums.Day;
import Enums.Type;

import java.time.LocalTime;

public class Lesson implements Comparable<Lesson> {
    public LocalTime time;
    Type type;
    Day day;
    String room;

    String scheduleDuration;
    public Lesson(int hour,int min, Type type, Day day, String room) {
        this.type = type;
        this.day = day;
        this.room = room;
        LocalTime time = LocalTime.of(hour, min);
    }
    public int getMinute()
    {
        return this.time.getMinute();
    }
    public int getHour()
    {
        return this.time.getHour();
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
}
