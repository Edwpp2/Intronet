package Core;

import Enums.Day;
import Enums.LessonType;

public class Schedule {
    String[][] timeTable;
    int dayInWeek = 7;
    int hoursForWork = 12;


    public Schedule(){
        timeTable = new String[12][7];
    }
    public void addLesson(Lesson lesson){
        timeTable[Day.valueOf(lesson.day.name()).ordinal()]
    }

}
