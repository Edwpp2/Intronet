//package Frontend;
//import Core.*;
//import Users.Student;
//import Users.Teacher;
//import java.io.BufferedReader;
//import java.io.IOException;
//
//
//public class TeacherCurrentCourseGuiV2 {
//
//    public static void chooseCourse(Teacher teacher,BufferedReader input) throws IOException {//STAGE 0
//        SchduleDrawer.printInfoAboutTeacherCourses(teacher);
//        System.out.println("Choose an option:");
//        System.out.println("[1]Choose course");
//        System.out.println("[2]Back");
//        int command = InputVerification.intValueCheck(input.readLine());
//        if (command == 1) {
//            if (teacher.courses.size() > 0) {
//                System.out.println("Enter course number");
//                int courseNum = InputVerification.intValueCheck(input.readLine());
//                teacher.chooseCourse(courseNum);
//            } else {
//                System.out.println("NO CURRENT COURSES!");
//            }
//        } else if (command == 2) {
//            start = false;
//        } else {
//            System.out.println("WRONG NUMBER!");
//        }
//    }
//    public static void manageCourse(Teacher teacher,BufferedReader input,Course course) throws IOException {//STAGE1
//        System.out.println("Choose an option:");
//        System.out.println("[1]Manage course material");
//        System.out.println("[2]View all marks");
//        System.out.println("[3]Rate students");
//        System.out.println("[4]Finish course");
//        System.out.println("[5]Back");
//        int command = InputVerification.intValueCheck(input.readLine());
//        if (command == 1) {
//            manageMaterial(teacher,input);
//        } else if (command == 2) {
//            SchduleDrawer.printMarksForListOfStudents(course);
//        } else if (command == 3) {
//            rateStudent(input,teacher);
//        } else if (command == 4) {
//            finishCourse(course,teacher);
//        } else if (command == 5) {
//            internalStage = 0;
//            TeacherCurrentCourseGuiV2.course = null;
//        } else {
//            System.out.println("WRONG NUMBER!");
//        }
//    }
//    public static void manageMaterial(Teacher teacher,BufferedReader input) throws IOException {
//        System.out.println("Chose an option:");
//        System.out.println("[1]Add material");
//        System.out.println("[2]Remove material");
//        System.out.println("[3]Back");
//        int command = InputVerification.intValueCheck(input.readLine());
//        if (command == 1) {
//            addMaterial(input,teacher);
//        } else if (command == 2) {
//            removeMaterial(teacher,input);
//        } else if (command == 3) {
//            internalStage = 2;
//        } else {
//            System.out.println("WRONG NUMBER!");
//        }
//    }
//    public static void finishCourse(Course course,Teacher teacher){
//        teacher.finishCourse(course);
//    }
//    public static void chooseStudent(Teacher teacher,BufferedReader input) throws IOException {
//        SchduleDrawer.printStudentsOnCourse(course);
//        System.out.println("Enter student number:");
//        int studentIndex = InputVerification.intValueCheck(input.readLine());
//        student = teacher.chooseStudent(course,studentIndex);
//    }
//    public static void rateStudent(BufferedReader input,Teacher teacher) throws IOException {
//        SchduleDrawer.printMarksForCurrentStudent(student, course, 0, 0, (student.toString()).length());
//        System.out.println("Chose an option:");
//        System.out.println("[1]Put mark for first attestation");
//        System.out.println("[2]Put mark for first attestation");
//        System.out.println("[3]Put mark for final");
//        System.out.println("[4]Put absence");
//        System.out.println("[5]Back");
//        int command = InputVerification.intValueCheck(input.readLine());
//        if (command == 1) {
//            System.out.println("Enter mark for first attestation");
//            double mark = InputVerification.doubleValueCheck(input.readLine());
//            teacher.putMarkForFirstAtt(mark,student,course);
//        } else if (command == 2) {
//            System.out.println("Enter mark for second attestation");
//            double mark = InputVerification.doubleValueCheck(input.readLine());
//            teacher.putMarkForSecondAtt(mark,student,course);
//        } else if (command == 3) {
//            System.out.println("Enter mark for final");
//            double mark = InputVerification.doubleValueCheck(input.readLine());
//            teacher.putMarkForFinal(mark,student,course);
//        } else if (command == 4) {
//            teacher.putAbsence(student,course);
//        } else if (command == 5) {
//            internalStage = 2;
//        } else {
//            System.out.println("WRONG NUMBER!");
//        }
//    }
//    public static void addMaterial(BufferedReader input,Teacher teacher) throws IOException {
//        System.out.println("Enter file name");
//        String filename = input.readLine();
//        teacher.addMaterial(course,filename);
//    }
//    public static void removeMaterial(Teacher teacher,BufferedReader input) throws IOException {
//        SchduleDrawer.printMaterials(course);
//        System.out.println("Enter number of material:");
//        int materialNum = InputVerification.intValueCheck(input.readLine());
//        teacher.removeMaterial(course,materialNum);
//    }
//}
//
//
