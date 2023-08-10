package Core;

import Enums.Day;
import Users.Teacher;

import java.io.Serializable;
import java.util.HashSet;

public class Lesson implements Serializable {
    public Day day;
    public String room;

    public Teacher teacher;
    public int hour;

    public String name;
    public Lesson(Day day,String room,int hour){
        this.day = day;
        this.room = room;
        this.hour = hour;
    }
    public String toString()
    {
        if(teacher!=null){
            return name + " " + "(" + teacher.name + " " + teacher.surname + " "  + room + ")";
        }
        else {
            return name + " " + "(" + "VACANSY" +" "+ room + ")";
        }
    }

}
