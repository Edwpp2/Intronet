import java.util.HashMap;
import java.util.Map;
public class painSchedule {
        public static void main(String[] args) {
            Map<String, String> schedule = new HashMap<>();
            Map<String, String> startTime = new HashMap<>();

            // Add classes and start times to the schedule
            schedule.put("Math", "Monday");
            startTime.put("Math", "09:00 AM");

            schedule.put("Science", "Tuesday");
            startTime.put("Science", "10:30 AM");

            schedule.put("History", "Wednesday");
            startTime.put("History", "01:00 PM");

            schedule.put("English", "Thursday");
            startTime.put("English", "11:15 AM");

            schedule.put("Art", "Friday");
            startTime.put("Art", "02:30 PM");

            // Print the schedule in table format
            System.out.println("Time\t\t| Monday\t\t| Tuesday\t| Wednesday\t| Thursday\t| Friday");
            System.out.println("----------------------------------------------------------------------------------");
            String[] daysOfWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
            String[] timesOfDay = {"09:00 AM", "10:30 AM", "01:00 PM", "11:15 AM", "02:30 PM"};

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
        private static String getClassName(Map<String, String> schedule, Map<String, String> startTime,
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
    }

