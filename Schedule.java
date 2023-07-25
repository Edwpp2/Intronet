import Enums.Day;

import java.util.HashMap;
import java.util.Map;

public class Schedule {
    String[][] schedule;
    String[] daysOfWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday","Saturday"};
    String[] timesOfDay = {"09:00 AM", "10:00 AM", "11:00 AM", "12:00 AM", "1:00 AM","2:00 PM","3:00 PM","4:00 PM","5:00 PM","6:00 PM","7:00 PM","8:00 PM","9:00 PM"};

    {
        schedule = new String[6][13];
    }
    public void fillArray(){
        for (int i = 1;i <= 6;i++){
            schedule[0][i] = daysOfWeek[i-1];
        }
        schedule[0][0] = "Time";
        schedule[0][1] = "Monday";
        schedule[0][2] = "Tuesday";
        schedule[0][3] = "Wednesday";
        schedule[0][4] = "Thursday";
        schedule[0][5] = "Friday";
        schedule[0][6] = "Saturday";
        //add time
        for(int i = 0;i <= 12;i++){
            schedule[i+1][0] = timesOfDay[i];
        }
    }

    public  String getClassName(Map<String, String> schedule, Map<String, String> startTime,
                                       String day, String time) {
        for (Map.Entry<String, String> entry : schedule.entrySet()) {
            String className = entry.getKey();
            String classDay = entry.getValue();
            String classStartTime = startTime.get(className);
            if (classDay.equals(day) && classStartTime.equals(time)) {
                return className;
            }
        }
        return "";
    }
    public void addToSchedule(Lesson lesson){
        String time = timesOfDay[lesson.time.getHour()-9];
        String dayOfWeek = daysOfWeek[lesson.day.ordinal()-1];
    }
    public void printSchedule()
    {
        for (int i = 0; i < timesOfDay.length; i++) {
            System.out.print(timesOfDay[i] + "\t| ");
            for (int j = 0; j < daysOfWeek.length; j++) {
                String className = getClassName(schedule, startTime, daysOfWeek[j], timesOfDay[i]);
                System.out.print(className + "\t\t| ");
            }
            System.out.println();
            if (i < timesOfDay.length - 1) {
                System.out.println("----------------------------------------------------------------------------------");
            }
        }
    }
}
