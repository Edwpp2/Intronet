package Frontend;

import Core.Course;
import Core.Intronet;
import Core.Schedule;
import Users.Student;
import Users.User;

public class SchduleDrawer {

    public SchduleDrawer(){};
    public static String formCellForSchedule(String content,Schedule schedule,int columnNumber)
    {
        String text ="";
        int length = Math.max(schedule.getLenghtOfColumn(columnNumber),8);
        String leftAlignFormat = "| " + "%-" + (length) + "s |";
        for (int i = 0; i < 5; i++) {
            text = String.format(leftAlignFormat, content, i * i);
        }
        return text;
    }
    public static String formCellForCourse(String content,int columnLenght)
    {
        String cell ="";
        int length = Math.max(columnLenght,1);
        String leftAlignFormat = "| " + "%-" + (length) + "s |";
        for (int i = 0; i < 5; i++) {
            cell = String.format(leftAlignFormat, content, i * i);
        }
        return cell;
    }


    public static void printSchedule(Schedule schedule){
        String[] dayHeader = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
        String[] time = {"Time","9:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00","20:00","21:00"};
        for(int i = 0; i < 13;i++){
            String header = "";
            String content = " " + formCellForSchedule(time[i+1],schedule,7);
            String daysHeader = " " + formCellForSchedule(time[i],schedule,7);
            for (int j = 0; j < 7;j++){
                String value = schedule.getTimeTable()[i][j];
                content += " " + formCellForSchedule(value,schedule,j);
                daysHeader += " " + formCellForSchedule(dayHeader[j],schedule,j);
            }
            String s = " " + "+" + "-".repeat(content.length() - 3) + "+";
            header += s;
            if(i==0){
                System.out.print(header);
                System.out.print("\n");
                System.out.print(daysHeader);
                System.out.print("\n");
            }
            
            System.out.print(header);
            System.out.print("\n");
            System.out.print(content);
            System.out.print("\n");
            if(i == 12){
                System.out.print(s);
            }
        }
    }
    public static void printCoursesForStudent(Student student){
        String[] headerArr = {"#","Name","Id","Capacity"};
        int nameLength = student.maxCourseName();
        int idLength = student.maxCourseId();
        String topHeader = "";
        String content = "";
        topHeader+= formCellForCourse(headerArr[0],1) + formCellForCourse(headerArr[1],nameLength) + formCellForCourse(headerArr[2],idLength) + formCellForCourse(headerArr[3],5);
        String delim = "+" + "-".repeat(topHeader.length() - 2) + "+";
        for(int i = 0; i < student.courses.size();i++){
            Course course = Intronet.getCourseById(student.courses.get(i));
            int studentsNum = course.studentMarks.keySet().size();
            int capacity = course.capacity;
            content += formCellForCourse("1",1) + formCellForCourse(course.name,nameLength) + formCellForCourse(course.getId(),idLength) + formCellForCourse(studentsNum + "/" + capacity,headerArr[3].length());
            if(i==0){
                System.out.print(delim);
                System.out.print("\n");
                System.out.print(topHeader);
                System.out.print("\n");
                System.out.print(delim);
                System.out.print("\n");
            }
            System.out.print(content);
            System.out.print("\n");
            if(i==student.courses.size()-1){
                System.out.print(delim);
                System.out.print("\n");
            }
        }


    }
    public static void printUsersForSystem(){
        String[] headerArr = {"#","Name","Role","Login"};
        int nameLength = Intronet.maxUserName;
        String topHeader = "";
        String content = "";
        topHeader+= formCellForCourse(headerArr[0],1) + formCellForCourse(headerArr[1],nameLength) + formCellForCourse(headerArr[2],12) + formCellForCourse(headerArr[3],11);
        String delim = "+" + "-".repeat(topHeader.length() - 2) + "+";
        for(int i = 0;i < Intronet.users.size();i++){
            User user = Intronet.users.get(i);
            String name = user.name + " " + user.surname;
            content += formCellForCourse("1",1) + formCellForCourse(name,nameLength) + formCellForCourse(user.role.name(),12) + formCellForCourse(user.login,11);
            if(i==0){
                System.out.print(delim);
                System.out.print("\n");
                System.out.print(topHeader);
                System.out.print("\n");
                System.out.print(delim);
                System.out.print("\n");
            }
            System.out.print(content);
            System.out.print("\n");
            if(i==Intronet.users.size()-1){
                System.out.print(delim);
                System.out.print("\n");
            }
        }
    }
    public void printCoursesInSystem(){
        String[] headerArr = {"#","Id","Credits","Teacher","Faculty","Students","Can join"};
        int courseNameLength = Intronet.maxCourseName();
        int courseIdLength = Intronet.maxCourseId();
        String topHeader = "";
        topHeader+= formCellForCourse(headerArr[0],1) + formCellForCourse(headerArr[1],courseIdLength) + formCellForCourse(headerArr[2],4) + formCellForCourse(headerArr[3],11);
        String delim = "+" + "-".repeat(topHeader.length() - 2) + "+";
    }


}

