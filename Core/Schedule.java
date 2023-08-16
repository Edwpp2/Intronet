package Core;

import Enums.Day;

import java.io.Serializable;

public class Schedule implements Serializable {
    Lesson[][] timeTable;
    int[] lengthOfColumn;
    int dayInWeek = 7;
    int hoursForWork = 13;
    public Schedule(){
        this.timeTable = new Lesson[hoursForWork][dayInWeek];
        this.lengthOfColumn = new int[8];
        lengthOfColumn[7] = 0;
    }
    public int getLengthOfColumn(int i){
        return lengthOfColumn[i];
    }
    public void addLesson(Lesson lesson){
        timeTable[lesson.hour][Day.valueOf(lesson.day.name()).ordinal()] = lesson;
        setMaxColumnLength(lesson);
    }
    public void setMaxColumnLength(Lesson lesson){
        if(lesson!=null){
            if(lengthOfColumn[Day.valueOf(lesson.day.name()).ordinal()]<lesson.toString().length()){
                lengthOfColumn[Day.valueOf(lesson.day.name()).ordinal()]=lesson.toString().length();
            }
        }
    }
    public void updateColumnLength(){

        for(int i = 0; i < 12; i ++){
            for (int j = 0; j < 7; j++){
                setMaxColumnLength(this.timeTable[i][j]);
            }
        }
    }
    public void dropLesson(int hour,int day){
        this.timeTable[hour][day]=null;
    }
    public Lesson[][] getTimeTable(){
        return this.timeTable;
    }
    public boolean checkCohesion(Schedule schedule1,Schedule schedule2) {
        Lesson[][] timetable1 = schedule1.getTimeTable();
        Lesson[][] timetable2 = schedule2.getTimeTable();
        for(int i = 0; i < 12; i ++){
            for (int j = 0; j < 7; j++){
                if(timetable1[i][j]!=null&& timetable2[i][j]!=null ){
                    return true;
                }
            }
        }
        return false;
    }
    public void cleanSchedule(Schedule courseSchedule){
        Lesson[][] timetable1 = courseSchedule.getTimeTable();
        Lesson[][] timetable2 = this.getTimeTable();
        for(int i = 0; i < 12; i ++){
            for (int j = 0; j < 7; j++){
                if(timetable1[i][j] != null && timetable2[i][j] != null ){
                    timetable2[i][j]=null;
                }
            }
        }
    }
    public boolean checkCohesion(Schedule schedule){
        return checkCohesion(this,schedule);
    }
    public boolean isEmpty(int hour,int day){
        return timeTable[hour][day]==null;
    }
}
