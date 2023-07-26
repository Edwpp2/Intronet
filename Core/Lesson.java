package Core;

import Enums.Day;
import Users.Teacher;

public class Lesson {
    public Day day;
    public String room;
    public Teacher teacher;
    public int hour;

    public String name;
    public Lesson(String name,Day day,String room,int hour){
        this.day = day;
        this.room = room;
        this.name = name ;
        this.hour = hour;
    }
    public String toString()
    {
        return name + " " + "(" + room + ")";
    }

}
