package Frontend;

import Core.Schedule;

public class SchduleDrawer {

    public  static String[] dayHeader = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
    public  static String[] time = {"9:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00","20:00","21:00"};

    public SchduleDrawer(){};
    public static String formCell(String text,String value,Schedule schedule,int k)
    {
        int valuee = schedule.getLenghtOfColumn(k);
        if(valuee<=8){
            valuee = 8;
        }
        String leftAlignFormat = "| " + "%-" + (valuee) + "s |";
        String cell = null;
        for (int i = 0; i < 5; i++) {
            text = String.format(leftAlignFormat, value, i * i);
        }

        return text;

    }

    public static void cellFill(Schedule schedule){

        String[][] scheduleWithCell = new String[12][7];
        boolean headerDone = true;
        for(int i = 0; i < 12;i++){
            String header = "";
            String content = " " + formCell(time[i],time[i],schedule,7);
            String daysHeader = " " + formCell(time[i],time[i],schedule,7);
            for (int j = 0; j < 7;j++){
                String value = schedule.getTimeTable()[i][j];
                content += " " + formCell(value,value,schedule,j);
                daysHeader += " " + formCell(value,dayHeader[j],schedule,j);
            }
            header += " " + "+" + "-".repeat(content.length()-3) + "+";
            if(headerDone){
                System.out.print(header);
                System.out.print("\n");
                System.out.print(daysHeader);
                System.out.print("\n");
                headerDone=false;
            }
            System.out.print(header);
            System.out.print("\n");
            System.out.print(content);
            System.out.print("\n");
            if(i == 11){
                System.out.print(" " + "+" + "-".repeat(content.length()-3) + "+");
            }
        }
    }
}

