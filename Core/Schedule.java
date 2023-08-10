package Core;

import Enums.Day;

public class Schedule {
    String[][] timeTable;
    int[] lenghtOfColumn;
    int dayInWeek = 7;
    int hoursForWork = 13;
    public Schedule(){
        this.timeTable = new String[hoursForWork][dayInWeek];
        this.lenghtOfColumn = new int[8];
        lenghtOfColumn[7] = 0;
        fillSchedule();
    }
    public int getLenghtOfColumn(int i){
        return lenghtOfColumn[i];
    }
    public void fillSchedule(){
        for (int i = 0; i < 13;i++){
            for(int j = 0; j < 7;j++){
                this.timeTable[i][j] = "-";
            }
        }
    }
    public void addLesson(Lesson lesson){
        timeTable[lesson.hour][Day.valueOf(lesson.day.name()).ordinal()] = lesson.toString();
        if(lenghtOfColumn[Day.valueOf(lesson.day.name()).ordinal()]<lesson.toString().length()){
            lenghtOfColumn[Day.valueOf(lesson.day.name()).ordinal()]=lesson.toString().length();
        }
    }
    public void dropLesson(Lesson lesson){
        int hour = lesson.hour;
        int day = Day.valueOf(lesson.day.name()).ordinal();
        timeTable[hour][day]="-";
    }
    public String[][] getTimeTable(){
        return this.timeTable;
    }
    public boolean checkCohesion(Schedule schedule1,Schedule schedule2) {
        String[][] timetable1 = schedule1.getTimeTable();
        String[][] timetable2 = schedule2.getTimeTable();
        for(int i = 0; i < 12; i ++){
            for (int j = 0; j < 7; j++){
                if(timetable1[i][j] != "-" && timetable2[i][j] != "-" ){
                    return true;
                }
            }
        }
        return false;
    }
    public void cleanSchedule(Schedule courseSchedule){
        String[][] timetable1 = courseSchedule.getTimeTable();
        String[][] timetable2 = this.getTimeTable();
        for(int i = 0; i < 12; i ++){
            for (int j = 0; j < 7; j++){
                if(timetable1[i][j] != "-" && timetable2[i][j] != "-" ){
                    timetable2[i][j]="-";
                }
            }
        }

    }
    public boolean checkCohesion(Schedule schedule){
        return checkCohesion(this,schedule);
    }
    public boolean isEmpty(Lesson lesson){
        int hour = lesson.hour;
        int day = Day.valueOf(lesson.day.name()).ordinal();
        return timeTable[hour][day]==null;
    }




}
