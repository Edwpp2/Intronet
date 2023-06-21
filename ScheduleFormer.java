import Enums.Day;
public class ScheduleFormer {

    public static String[][] createSchdule(){
        String[][] schedule = {
                {"TIME", "Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"},
                {"09:00", null, null, null, null, null, null,null},
                {"10:00", null, null, null, null, null, null,null},
                {"11:00", null, null, null, null, null, null,null},
                {"12:00", null, null, null, null, null, null,null},
                {"13:00", null, null, null, null, null, null,null},
                {"14:00", null, null, null, null, null, null,null},
                {"15:00", null, null, null, null, null, null,null},
                {"16:00", null, null, null, null, null, null,null},
                {"17:00", null, null, null, null, null, null,null},
                {"18:00", null, null, null, null, null, null,null},
                {"19:00", null, null, null, null, null, null,null},
                {"20:00", null, null, null, null, null, null,null},
                {"21:00", null, null, null, null, null, null,null}
        };
        return schedule;
    }
    public void updateSchedule(Lesson lesson,String[][] schedule){
        int i = 0;
        int j = 0;
        if(lesson.day==Day.MONDAY){
            i = 1;
        } else if (lesson.day==Day.TUESDAY) {
            i = 2;
        }else if (lesson.day==Day.WEDNESDAY) {
            i = 3;
        }else if (lesson.day==Day.THURSDAY) {
            i = 4;
        }else if (lesson.day==Day.FRIDAY) {
            i = 5;
        }else if (lesson.day==Day.SATURDAY) {
            i = 6;
        }else if (lesson.day==Day.SUNDAY) {
            i = 7;
        }
        if(lesson.time.getHour()==9){
            j = 1;
        } else if (lesson.time.getHour()==10) {
            j = 2;
        }else if (lesson.time.getHour()==11) {
            j = 3;
        }else if (lesson.time.getHour()==12) {
            j = 4;
        }else if (lesson.time.getHour()==13) {
            j = 5;
        }else if (lesson.time.getHour()==14) {
            j = 6;
        }else if (lesson.time.getHour()==15) {
            j = 7;
        }else if (lesson.time.getHour()==16) {
            j = 8;
        }else if (lesson.time.getHour()==17) {
            j = 9;
        }else if (lesson.time.getHour()==18) {
            j = 10;
        }else if (lesson.time.getHour()==19) {
            j = 11;
        }else if (lesson.time.getHour()==20) {
            j = 12;
        }else if (lesson.time.getHour()==21) {
            j = 13;
        }
        schedule[i][j] = lesson.toString();
    }
    public static void viewSchedule(String[][] schedule){
        for (int i = 0; i < 8;i++)
        {
            for (int j = 0;j < 14;j++){
                if(schedule[i][j]!=null){
                    System.out.println(schedule[i][j]);
                }
                else {
                    System.out.println("EMPTY SLOT");
                }
            }
        }

    }
}
