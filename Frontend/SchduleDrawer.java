package Frontend;
import Core.Course;
import Core.Intronet;
import Core.Lesson;
import Core.Schedule;
import Users.Student;
import Users.User;

public class SchduleDrawer {

    public static void print(int i,String content,String delim,String topHeader,int limit){
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
        System.out.print(delim);
        System.out.print("\n");
        if(i==limit){
            System.out.print("\n");
        }
    }
    public SchduleDrawer(){};
    public static String toCell(String content,Schedule schedule,int columnNumber)
    {
        String cell ="";
        int length = Math.max(schedule.getLenghtOfColumn(columnNumber),8);
        String leftAlignFormat = "| " + "%-" + (length) + "s |";
        for (int i = 0; i < 5; i++) {
            cell = String.format(leftAlignFormat, content, i * i);
        }
        return cell;
    }
    public static String toCell(String content,int columnLenght)
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
        String delim = "";
        String content="";
        String topHeader="";
        for(int i = 0; i < 13;i++){
            content = " " + toCell(time[i+1],schedule,7);
            topHeader = " " + toCell(time[i],schedule,7);
            for (int j = 0; j < 7;j++){
                content += " " + toCell(schedule.getTimeTable()[i][j],schedule,j);
                topHeader += " " + toCell(dayHeader[j],schedule,j);
            }
            delim = " " + "+" + "-".repeat(content.length() - 3) + "+";;
            print(i,content,delim,topHeader,12);
        }
    }
    public static void printInfoAboutStudentCourses(Student student){
        int nameLength = student.maxCourseName();
        String number=toCell("#",1);
        String name=toCell("Name",nameLength);
        String id = toCell("Id",9);
        String capacity = toCell("Students on course","Students on course".length());
        String code = toCell("Code",12);
        String courseFilesCount = toCell("Course file count","Course file count".length());
        String topHeader= number + id + code + name + capacity + courseFilesCount;

        String delim = "+" + "-".repeat(topHeader.length() - 2) + "+";
        for(int i = 0; i < student.courses.size();i++){
            Course course = Intronet.getCourseById(student.courses.get(i));
            number=toCell(1+i+"",1);
            name=toCell(course.name,nameLength);
            id = toCell(course.getId(),9);
            capacity = toCell(course.studentMarks.size() + "/" + course.capacity,"Students on course".length());
            code = toCell(course.code,12);
            courseFilesCount = toCell(course.materials.size()+"","Course file count".length());
            String content = number + id + code + name + capacity + courseFilesCount;
            print(i,content,delim,topHeader,Intronet.courses.size());
        }
    }
    public static void printInfoAboutCourse(Course course){
        int nameLength = course.name.length();
        String number=toCell("#",1);
        String name=toCell("Name",nameLength);
        String id = toCell("Id",9);
        String capacity = toCell("Students on course","Students on course".length());
        String code = toCell("Code",12);
        String courseFilesCount = toCell("Course file count","Course file count".length());
        String topHeader= number + id + code + name + capacity + courseFilesCount;

        String delim = "+" + "-".repeat(topHeader.length() - 2) + "+";
        number=toCell(1+"",1);
        name=toCell(course.name,nameLength);
        id = toCell(course.getId(),9);
        capacity = toCell(course.studentMarks.size() + "/" + course.capacity,"Students on course".length());
        code = toCell(course.code,12);
        courseFilesCount = toCell(course.materials.size()+"","Course file count".length());

        String content = number + id + code + name + capacity + courseFilesCount;
        print(0,content,delim,topHeader,0);
    }
    public static void printUsersForSystem(){
        int nameLength = Intronet.maxUserName;
        String number=toCell("#",1);
        String name=toCell("Name",nameLength);
        String id = toCell("Id",12);
        String role= toCell("Role",12);
        String login = toCell("Login",11);
        String isBlocked= toCell("Blocked","Blocked".length());
        String topHeader= number + name+ id + role + login+ isBlocked;
        String delim = "+" + "-".repeat(topHeader.length() - 2) + "+";

        for(int i = 0;i < Intronet.users.size();i++){
            User user = Intronet.users.get(i);
            number = toCell(i+1+"",1);
            name = toCell(user.name + " " + user.surname,nameLength);
            id = toCell(user.getId(),12);
            role = toCell(user.role.name(),12);
            login = toCell(user.login,11);
            isBlocked = toCell(user.isBlocked()?"Yes":"No",7);
            String content = number + name + id + role + login + isBlocked;
            print(i,content,delim,topHeader,Intronet.users.size());
        }
    }
    public static void printCoursesForRegistration(Student student){
        int courseNameLength = Intronet.maxCourseName();
        String number = toCell("#",1);
        String code = toCell("Code",8);
        String title = toCell("Title",courseNameLength);
        String credits = toCell("Credits","Credits".length());
        String teacher = toCell("Teacher",14);
        String faculty =  toCell("Faculty","Faculty".length());
        String students = toCell("Students","Students".length());
        String prerequisites = toCell("Prerequisites",13);
        String canJoin = toCell("Can join","Can join".length());

        String topHeader= number + code + title + credits + teacher + faculty + prerequisites + students + canJoin;
        String delim = "+" + "-".repeat(topHeader.length() - 2) + "+";

        for(int i = 0; i < Intronet.courses.size();i++){
            Course course = Intronet.courses.get(i);
            number = toCell(1+i+"",1);
            code = toCell(course.code,8);
            title = toCell(course.name,courseNameLength);
            credits = toCell(course.credits+"","Credits".length());
            faculty =  toCell(course.faculty.name(),"Faculty".length());
            students = toCell(course.studentMarks.size() + "/" + course.capacity,"Students".length());
            canJoin = toCell(course.canJoin(student)?"Yes":"No","Can join".length());
            teacher = "";
            for (Lesson lesson : course.lessons){
                if(lesson.teacher!=null){
                    teacher+= ", " + lesson.teacher.name;
                }
            }
            if(teacher==""){
                teacher="Vacancy";
            }
            prerequisites = "[ ";
            for(String prerec : course.prerecs){
                prerequisites+= ", " + Intronet.getCourseById(prerec);
            }
            prerequisites = toCell(prerequisites + " ]",13);
            teacher = toCell(teacher,14);
            String content = number + code + title + credits + teacher + faculty + prerequisites + students + canJoin;
            print(i,content,delim,topHeader,Intronet.courses.size());
        }
    }
    public static void printMaterials(Course course)
    {
        int materialNameLength = course.maxMaterialName();
        String number = toCell("#",1);
        String name = toCell("Name",materialNameLength);
        String url = toCell("Url",materialNameLength + "https://yandex.kz/".length());
        String topHeader = number + name + url;
        String delim = "+" + "-".repeat(topHeader.length() - 2) + "+";
        for(int i = 0;i < course.materials.size();i++){
            number = toCell(i+1+"",1);
            name = toCell(course.materials.get(i),materialNameLength);
            url =  toCell("https://yandex.kz/" + course.materials.get(i) ,materialNameLength + "https://yandex.kz/".length());
            String content = number + name + url;
            print(i,content,delim,topHeader,Intronet.users.size()-1);
        }
    }
}

