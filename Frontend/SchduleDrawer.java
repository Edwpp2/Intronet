package Frontend;
import Core.*;
import Enums.Role;
import Users.Student;
import Users.StudyPerson;
import Users.Teacher;
import Users.User;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Vector;

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
    public SchduleDrawer(){}
    public static String toCell(String content,Schedule schedule,int columnNumber)
    {
        String cell ="";
        int length = Math.max(schedule.getLengthOfColumn(columnNumber),9);
        String leftAlignFormat = "| " + "%-" + (length) + "s |";
        for (int i = 0; i < 5; i++) {
            cell = String.format(leftAlignFormat, content, i * i);
        }
        return cell;
    }
    public static String toCell(String content,int columnLength)
    {
        String cell ="";
        int length = Math.max(columnLength,content.length());
        String leftAlignFormat = "| " + "%-" + (length) + "s |";
        for (int i = 0; i < 5; i++) {
            cell = String.format(leftAlignFormat, content, i * i);
        }
        return cell;
    }
    public static void printSchedule(Schedule schedule){
        String[] dayHeader = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
        String[] time = {"Time","9:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00","19:00","20:00","21:00"};
        String delim;
        StringBuilder content;
        StringBuilder topHeader;
        schedule.updateColumnLength();
        for(int i = 0; i < 13;i++){
            content = new StringBuilder(" " + toCell(time[i + 1], schedule, 7));
            topHeader = new StringBuilder(" " + toCell(time[i], schedule, 7));
            for (int j = 0; j < 7;j++){
                if(schedule.getTimeTable()[i][j]!=null){
                    content.append(" ").append(toCell(schedule.getTimeTable()[i][j].toString(), schedule, j));
                    topHeader.append(" ").append(toCell(dayHeader[j], schedule, j));
                }
                else {
                    content.append(" ").append(toCell("-", schedule, j));
                    topHeader.append(" ").append(toCell(dayHeader[j], schedule, j));
                }

            }
            delim = " " + "+" + "-".repeat(content.length() - 3) + "+";
            print(i, content.toString(),delim, topHeader.toString(),12);
        }
    }
    //DONE
    public static void printInfoAboutStudentCourses(Student student){
        int maxNameLength = 0;
        int maxCodeLength = 0;
        int i = 0;
        if(student.courses.size()>0){
            for (String courseId : student.courses.keySet()){
                Course course = Intranet.getInstance().getCourseById(courseId);
                maxNameLength = Math.max(maxNameLength,course.name.length());
                maxCodeLength = Math.max( maxCodeLength,course.code.length());
            }
            for (String courseId : student.courses.keySet()){
                Course course = Intranet.getInstance().getCourseById(courseId);
                printInfoAboutCourse(course,maxNameLength,maxCodeLength,i,student.courses.size(),false);
                i++;
            }
        }
        else {
            System.out.println("NO COURSES!");
        }
    }
    public static void printUserInfo(User user,int userNumber,int userAmount,int maxUserNameLength,int maxUserLogin, int maxUserPassword,boolean userCreation){
        String number=toCell("#",3);
        String name=toCell("Name",maxUserNameLength);
        String id = toCell("Id",10);
        String role= toCell("Role",7);
        String login = toCell("Login",maxUserLogin);
        String faculty = toCell("Faculty",11);
        String isBlocked = toCell( "Blocked","Blocked".length());
        String degree = toCell("Degree",11);
        String password = toCell("Password",maxUserPassword);
        String content;
        String topHeader;
        if(userCreation){
            topHeader = number + id + name+ login + password + role + faculty + degree;
        }
        else {
            topHeader = number + id  + name + role + faculty + login + isBlocked;
        }
        String delim = "+" + "-".repeat(topHeader.length() - 2) + "+";
        number = toCell(1+userNumber+"",3);
        name = toCell(user.toString(),maxUserNameLength);
        id = toCell(user.getId(),10);
        role = toCell(user.getRole().name(),7);
        login = toCell(user.getLogin(),maxUserLogin);
        faculty = toCell(user.getFaculty().name(),11);
        isBlocked = toCell(user.isBlocked()?"Yes":"No",7);
        if(userCreation){
            password = toCell(user.getPassword(),maxUserPassword);
            if(user.getRole()==Role.STUDENT||user.getRole()==Role.TEACHER){
                degree = toCell(((StudyPerson)user).getDegree().toString(),11);
            }
            else {
                degree = toCell("NO DEGREE",11);
            }
            content = number + id + name+ login + password + role  + faculty + degree;

        }
        else {
            content = number + id  + name + role + faculty + login + isBlocked;
        }
        print(userNumber,content,delim,topHeader,userAmount);
    }
    //DONE
    public static void printInfoAboutTeacherCourses(Teacher teacher){
        int maxNameLength = 0;
        int maxCodeLength = 0;
        int i = 0;
        if(teacher.courses.size()>0){
            for (String courseId : teacher.courses){
                Course course = Intranet.getInstance().getCourseById(courseId);
                maxNameLength = Math.max(maxNameLength,course.name.length());
                maxCodeLength = Math.max( maxCodeLength,course.code.length());
            }
            for (String courseId : teacher.courses){
                Course course = Intranet.getInstance().getCourseById(courseId);
                printInfoAboutCourse(course,maxNameLength,maxCodeLength,i,teacher.courses.size(),false);
                i++;
            }
        }
        else {
            System.out.println("NO COURSES!");
        }
    }
    //DONE
    public static void printInfoAboutCourse(Course course,int maxNameLength,int maxCodeLength,int courseNumber,int courseAmount,boolean courseCreation){

        String number=toCell("#",3);
        String name=toCell("Name",maxNameLength);
        String id = toCell("Id",13);
        String capacity = toCell("Students on course","Students on course".length());
        String code = toCell("Code",maxCodeLength);
        String courseFilesCount = toCell("Course file count","Course file count".length());
        String faculty = toCell("Faculty","Faculty".length());
        String credits = toCell("Credits","Credits".length());
        String topHeader;
        String content;
        if(courseCreation){
            topHeader = number + faculty + name + credits + capacity + code;
        }
        else {
            topHeader = number + id + faculty + code + name + capacity + courseFilesCount;
        }
        String delim = "+" + "-".repeat(topHeader.length() - 2) + "+";
        faculty = toCell(course.faculty.name(),"Faculty".length());
        name=toCell(course.name,maxNameLength);
        id = toCell(course.getId(),13);
        capacity = toCell(course.studentMarks.size() + "/" + course.capacity,"Students on course".length());
        code = toCell(course.code,maxCodeLength);
        number = toCell(courseNumber+1+"",3);
        if(courseCreation){
            credits = toCell(course.credits+"","Credits".length());
            content = number + faculty + name + credits + capacity + code;
        }
        else {
            courseFilesCount = toCell(course.materials.size()+"","Course file count".length());
            content = number + id + faculty + code + name + capacity + courseFilesCount;

        }
        print(courseNumber,content,delim,topHeader,courseAmount);
    }

    public static void printUsersForSystem(Vector<User> users){
        if(Intranet.getInstance().users.size()>0){
            int maxUserName = 0;
            int maxUserLogin = 0;
            int maxUserPassword = 0;
            for(User user: Intranet.getInstance().users)
            {
                maxUserName = Math.max(maxUserName,(user.toString()).length());
                maxUserLogin = Math.max(maxUserLogin,user.getLogin().length());
                maxUserPassword = Math.max(maxUserPassword,user.getPassword().length());
            }
            for(int i = 0;i < users.size();i++){
                printUserInfo(users.get(i),i,users.size(),maxUserName,maxUserLogin,maxUserPassword,false);
            }
        }
        else {
            System.out.println("NO USERS IN SYSTEM!");
        }
    }
    public static void printAllCoursesInSystem(){
        if(Intranet.getInstance().courses.size()>0){
            int maxCourseName = 0;
            int maxCode = 0;
            for(Course course : Intranet.getInstance().courses)
            {
                maxCourseName = Math.max(maxCourseName,course.name.length());
                maxCode = Math.max(maxCode,course.code.length());
            }
            int i = 0;
            for(Course course: Intranet.getInstance().courses){
                printInfoAboutCourse(course,maxCourseName,maxCode,i,Intranet.getInstance().courses.size(),false);
                i++;
            }
        }
        else {
            System.out.println("NO COURSES IN SYSTEM!");
        }
    }
    //DONE
    public static void printStudentsOnCourse(Course course){
        int maxStudentName = 0;
        Set<String> studentsIds = course.studentMarks.keySet();
        for(String studentId : studentsIds)
        {
            User student = Intranet.getInstance().getUserById(studentId);
            maxStudentName = Math.max(maxStudentName,(student.toString()).length());
        }
        String studentName = toCell("Student name", maxStudentName);
        String number = toCell("#",1);
        String id = toCell("Student id",13);
        String topHeader = number + studentName + id;
        String delim = "+" + "-".repeat(topHeader.length() - 2) + "+";
        if(studentsIds.size()>0){
            int i = 0;
            for (String studentId : course.studentMarks.keySet()){
                User student = Intranet.getInstance().getUserById(studentId);
                studentName = toCell(student.toString(),maxStudentName);
                id = toCell(student.getId(),13);
                number = toCell(1+i+"",1);
                String content = number + studentName + id;
                print(i,content,delim,topHeader, Intranet.getInstance().courses.size());
                i++;
            }
        }
        else {
            System.out.println("NO STUDENTS ON COURSE!");
        }

    }
    public static int[] getFieldLength(List<String> courses){
        String code = "Code";
        String title = "Title";
        String teacher = "Teacher";
        StringBuilder prerequisites = new StringBuilder("Prerequisites");
        int[] columnLength = new int[4];
        columnLength[0] = code.length();
        columnLength[1] = title.length();
        columnLength[2] = teacher.length();
        columnLength[3] = prerequisites.length();
        for (String courseId : courses) {
            Course course = Intranet.getInstance().getCourseById(courseId);
            code = course.code;
            title = course.name;
            if (course.teacher == null) {
                teacher = "Vacancy";
            } else {
                teacher = course.teacher.toString();
            }
            prerequisites = new StringBuilder("[ ");
            for (String prerec : course.prerecs) {
                prerequisites.append(", ").append(Intranet.getInstance().getCourseById(prerec));
            }
            prerequisites.append(" ]");
            columnLength[0] = Math.max(columnLength[0], code.length());
            columnLength[1] = Math.max(columnLength[1], title.length());
            columnLength[2] = Math.max(columnLength[2], teacher.length());
            columnLength[3] = Math.max(columnLength[3], prerequisites.length());
        }
        return columnLength;
    }
    //DONE
    public static void printCoursesForRegistration(Student student){
        List<String> courses = Intranet.getInstance().availableCourses(student);
        int[] columnLength = getFieldLength(courses);
        String number = toCell("#",1);
        String code = toCell("Code",columnLength[0]);
        String title = toCell("Title", columnLength[1]);
        String credits = toCell("Credits","Credits".length());
        String teacher = toCell("Teacher", columnLength[2]);
        String faculty =  toCell("Faculty","Faculty".length());
        String students = toCell("Students","Students".length());
        StringBuilder prerequisites = new StringBuilder(toCell("Prerequisites", columnLength[3]));
        String canJoin = toCell("Can join","Can join".length());
        String topHeader= number + code + title + credits + teacher + faculty + prerequisites + students + canJoin;
        String delim = "+" + "-".repeat(topHeader.length() - 2) + "+";
        if(courses.size()>0){
            for(int i = 0; i < courses.size();i++){
                Course course = Intranet.getInstance().getCourseById(courses.get(i));
                number = toCell(1+i+"",1);
                code = toCell(course.code,columnLength[0]);
                title = toCell(course.name,columnLength[1]);
                credits = toCell(course.credits+"","Credits".length());
                faculty =  toCell(course.faculty.name(),"Faculty".length());
                students = toCell(course.studentMarks.size() + "/" + course.capacity,"Students".length());
                canJoin = toCell(course.canJoin(student)?"Yes":"No","Can join".length());
                if (course.teacher == null) {
                    teacher = "Vacancy";
                } else {
                    teacher = course.teacher.toString();
                }
                prerequisites = new StringBuilder("[ ");
                for(String prerec : course.prerecs){
                    prerequisites.append(", ").append(Intranet.getInstance().getCourseById(prerec));
                }
                prerequisites = new StringBuilder(toCell(prerequisites + " ]", columnLength[3]));
                teacher = toCell(teacher,columnLength[3]);
                String content = number + code + title + credits + teacher + faculty + prerequisites + students + canJoin;
                print(i,content,delim,topHeader, Intranet.getInstance().courses.size());
            }
        }
        else {
            System.out.println("NO COURSES!");
        }
    }

//    public static void printLesson(Lesson lesson,int lessonNumber,int limit){
//        String number = toCell("#",1);
//        String name = toCell("Name",8);
//        String time = toCell("Time",15);
//        String header = number + name + time;
//        String delim = "+" + "-".repeat(header.length() - 2) + "+";
//        time = lesson.hour+":00" + "  " + lesson.day;
//        String content = number + name + time;
//
//        print(lessonNumber,content,delim,header, Intranet.getInstance().courses.size());
//    }
    public static void printMaterials(Course course)
    {
        int maxLength = 0;
        for (Material material : course.materials){
            maxLength = Math.max(maxLength,material.title.length());

        }
        String number = toCell("#",1);
        String name = toCell("Name",maxLength);
        String url = toCell("Url",maxLength + "https://yandex.kz/".length());
        String topHeader = number + name + url;
        String delim = "+" + "-".repeat(topHeader.length() - 2) + "+";
        if(course.materials.size()>0) {
            for (int i = 0; i < course.materials.size(); i++) {
                number = toCell(i + 1 + "", 1);
                name = toCell(course.materials.get(i).title, maxLength);
                url = toCell("https://yandex.kz/" + course.materials.get(i).title,  maxLength + "https://yandex.kz/".length());
                String content = number + name + url;
                print(i, content, delim, topHeader, Intranet.getInstance().users.size() - 1);
            }
        }
        else {
            System.out.println("NO MATERIALS!");
        }
    }
    //DONE
    public static void printMarksForCurrentStudent(Student student,Course course,int numberOfStudents,int limit,int maxUserName){
        String firstAttAverage = toCell("No points","First att average".length());
        String secondAttAverage = toCell("No points","Second att average".length());
        StringBuilder firstAtt = new StringBuilder(toCell("No points", "First attestation".length()));
        StringBuilder secondAtt = new StringBuilder(toCell("No points", "Second attestation".length()));
        String number = toCell("#",1);

        Mark marks = course.studentMarks.get(student.getId());
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        if(marks.firstAtt.size()>0){
            firstAtt = new StringBuilder();
            for(Double mark : marks.firstAtt){
                firstAtt.append(mark).append(",");
            }
            firstAtt = new StringBuilder(toCell(firstAtt.toString(), "First attestation".length()));
            firstAttAverage = toCell(decimalFormat.format(course.studentMarks.get(student.getId()).getAverageForFirstAtt()),"First att average".length());
        }
        if(marks.secondAtt.size()>0){
            secondAtt = new StringBuilder();
            for(Double mark : marks.secondAtt){
                secondAtt.append(mark).append(",");
            }
            secondAtt = new StringBuilder(toCell(secondAtt.toString(), "Second attestation".length()));
            secondAttAverage = toCell(decimalFormat.format(course.studentMarks.get(student.getId()).getAverageForSecondAtt()),"Second att average".length());
        }

        String firstAttAverageHeader = toCell("First att average","First att average".length());
        String secondAttAverageHeader = toCell("Second att average","Second att average".length());
        String finalPointsHeader = toCell("Points for final","Points for final".length());
        String absentCountHeader =toCell("Absent count","Absent count".length());
        String studentNameHeader = toCell("Student name", maxUserName);
        String firstAttHeader= toCell("First attestation",firstAtt.length()-4);
        String secondAttHeader=toCell("Second attestation",secondAtt.length()-4);
        String topHeader =number + studentNameHeader + firstAttHeader + firstAttAverageHeader + secondAttHeader + secondAttAverageHeader + finalPointsHeader + absentCountHeader;
        String delim = "+" + "-".repeat(topHeader.length() - 2) + "+";

        number = toCell(numberOfStudents+1+"",1);
        String finalPoints = toCell(marks.pointsForFinal + "","Points for final".length());
        String absentCount = toCell(marks.absenceCount + "","Absent count".length());
        String studentName = toCell(student.toString(),maxUserName);
        String content =number + studentName + firstAtt + firstAttAverage + secondAtt + secondAttAverage + finalPoints + absentCount;
        print(numberOfStudents,content,delim,topHeader,limit);
    }

    public static void printMarksForListOfStudents(Course course){
        if(course.studentMarks.size()>0){
            int maxUserName = 0;
            Intranet intranet = Intranet.getInstance();
            Set<String> usersId = course.studentMarks.keySet();
            for (String userId :  usersId){
                User user = intranet.getUserById(userId);
                maxUserName = Math.max(maxUserName,(user.toString()).length());
            }
            for(int i = 0; i < course.studentMarks.keySet().size();i++){
                Student student = (Student) Intranet.getInstance().getUserById(((String)course.studentMarks.keySet().toArray()[i]));
                printMarksForCurrentStudent(student,course,i,course.studentMarks.keySet().size(),maxUserName);
            }
        }
        else{
            System.out.println("NO STUDENTS ON COURSE!");
        }
    }
    //DONE
    public static void printTeacherRatingForCourse(Course course,int numberOfStudents,int limit,int maxCourseName){
        try {
            String number = toCell("#",1);
            String courseName= toCell("Course name",maxCourseName);
            String rating = toCell("Rating","Rating".length());
            String topHeader = number + courseName + rating;
            String delim = "+" + "-".repeat(topHeader.length() - 2) + "+";
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            number = toCell(numberOfStudents+1+"",1);
            rating = toCell(decimalFormat.format(course.getTeacherRating()),6);
            courseName = toCell(course.name,maxCourseName);
            String content = number + courseName + rating;
            print(numberOfStudents,content,delim,topHeader,limit);
        }
        catch (NullPointerException e){
            System.out.println("No courses with rating!");
        }
    }
    public static void printTeacherRatingForAllCourses(Teacher teacher){
        int i =0;
        int maxCourseName = 0;
        for (String courseId : teacher.courses){
            maxCourseName = Math.max(maxCourseName,Intranet.getInstance().getCourseById(courseId).name.length());
        }
        if(teacher.courses.size()>0){
            for (String id:teacher.courses){
                Course course = Intranet.getInstance().getCourseById(id);
                printTeacherRatingForCourse(course,i,teacher.courses.size(),maxCourseName);
                i++;
            }
        }
        else {
            System.out.println("NO COURSES WITH RATING!");
        }

    }
    //DONE
    public static void printAvailableTeachers(Course course){
        List<Teacher> teachers = Intranet.getInstance().enableTeachers(course);
        int maxNameLength = 0;
        for (Teacher teacher : teachers){
            maxNameLength = Math.max((teacher.toString()).length(),maxNameLength);
        }
        String number = toCell("#",3);
        String teacherName = toCell("Name",maxNameLength);
        String faculty = toCell("Faculty",8);
        String degree = toCell("Degree",8);
        String topHeader = number + teacherName + faculty + degree;
        String delim = "+" + "-".repeat(topHeader.length() - 2) + "+";
        if(teachers.size()>0){
            for(int i = 0;i < teachers.size();i++){
                number=toCell(1+i+"",3);
                teacherName = toCell(teachers.get(i).toString(),maxNameLength);
                faculty = toCell(teachers.get(i).getFaculty().name(),8);
                degree = toCell(teachers.get(i).degree.name(),8);
                String content = number + teacherName + faculty + degree;
                print(i,content,delim,topHeader,teachers.size());
            }
        }
        else {
            System.out.println("No available teachers!");
        }
    }
    public static void printNews(int i,News news){
        String number = toCell(i+1+"",1);
        String title = toCell("Title:","Title:".length());
        String titleContent = toCell(news.title,120-title.length()-number.length());
        String header = number + title + titleContent;
        String content = toCell(news.content,120);
        String delim = "+" + "-".repeat(header.length() - 2) + "+";
        System.out.print(delim+"\n");
        System.out.print(header+"\n");
        System.out.print(delim+"\n");
        System.out.print(content+"\n");
        System.out.print(delim+"\n");

        if(news.comments.size()>0){
            System.out.println(toCell("Comments:",120));
            System.out.print(delim+"\n");
            int commentNumber = 0;
            for (String userId : news.comments.keySet()){
                User user = Intranet.getInstance().getUserById(userId);
                String numberNews = toCell(commentNumber+1+"",1);
                String comment = numberNews + toCell(user.toString() + " : " + news.comments.get(userId),120-numberNews.length()+3);
                System.out.println(comment);
                System.out.println(delim);
                commentNumber++;
            }
        }
    }
    public static void printTranscript(Student student){

        String code = toCell("Code",9);
        String credits = toCell("Credits",9);
        String points = toCell("Ponts",9);
        String letterPoints = toCell("Letter points","Letter points".length());
        String gpa = toCell("GPA",9);

        for (int yearOfStudy : student.transcript.keySet()){
            HashMap<String,Mark> courseAndMark = student.transcript.get(yearOfStudy);
            int i = 0;
            String courseName = toCell("Course name",student.maxTranscriptCourseName(yearOfStudy));
            String header = code + courseName + credits + points + letterPoints + gpa;
            String delim = "+" + "-".repeat(header.length() - 2) + "+";
            for (String courseId : courseAndMark.keySet()){

                Course course = Intranet.getInstance().getCourseById(courseId);
                courseName = toCell(course.name,student.maxCourseName());
                credits = toCell(course.credits+"",9);
                points = toCell(courseAndMark.get(course.getId()).getFinalPoint()+"",9);
                code = toCell(course.code,9);
                letterPoints = toCell(courseAndMark.get(course.getId()).getLatterMarks(courseAndMark.get(course.getId()).getFinalPoint()) + "", "Letter points".length());
                gpa = toCell(courseAndMark.get(course.getId()).getFinalPoint()+"",9);
                String content = code + courseName + credits + points + letterPoints + gpa;
                print(i,content,delim,header,courseAndMark.size());
                i++;
            }
        }
    }
}
