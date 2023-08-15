package Frontend;

import Comporators.FacultyComporator;
import Comporators.IdComporator;
import Comporators.NameComparator;
import Comporators.RoleComparator;
import Constructors.UserConstructor;
import Core.Course;
import Core.InputVerificator;
import Core.Intronet;
import Core.Lesson;
import Enums.Faculty;
import Enums.Role;
import Users.Student;
import Users.Teacher;
import Users.User;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.Vector;

public class ManagerUserManagmentGUI {
    public static void menu(User user,BufferedReader input) throws IOException {
        int internalStage = 0;
        int command;
        boolean teacherWasEdited = false;
        boolean studentWasEdited = false;
        boolean start = true;
        if(Intronet.getInstance().users.size()<1){
            start=false;
            System.out.println("NO USERS TO MANAGE!");
        }
        Vector<User> users = (Vector<User>) Intronet.getInstance().users.clone();
        while (start){
            if(internalStage==0){
                System.out.println("Choose an option");
                System.out.println("[1]View all users");
                System.out.println("[2]Back");
                command = InputVerificator.intValueCheck(input.readLine());
                if(command==1){
                    internalStage++;
                }
                if(command==2){
                    start=false;
                    user = null;
                    users = null;
                }
            }
            if(internalStage==1){
                SchduleDrawer.printUsersForSystem(users);
                System.out.println("Choose an option");
                System.out.println("[1]Edit user;");
                System.out.println("[2]Add user");
                System.out.println("[3]Sort user");
                System.out.println("[4]Back");
                command = InputVerificator.intValueCheck(input.readLine());
                if(command==1){
                    internalStage++;
                }
                else if(command==2){
                    internalStage=4;
                }
                else if(command==3){
                    System.out.println("How do you wont to sort users:");
                    System.out.println("[1]By id;");
                    System.out.println("[2]By name and surname;");
                    System.out.println("[3]By faculty;");
                    System.out.println("[4]By role;");
                    System.out.println("[5]Dont change.");
                    command = InputVerificator.intValueCheck(input.readLine());
                    if(command==1){
                        Comparator<User> IdComporator = new IdComporator();
                        users.sort(IdComporator);
                    }
                    else if(command==2){
                        Comparator<User> NameComparator = new NameComparator();
                        users.sort(NameComparator);
                    }
                    else if(command==3){
                        Comparator<User>FacultyComporator = new FacultyComporator();
                        users.sort(FacultyComporator);
                    }
                    else if(command==4){
                        Comparator<User> RoleComparator = new RoleComparator();
                        users.sort(RoleComparator);
                    }
                    else if(command==5){
                        continue;
                    }
                    else {
                        System.out.println("WRONG NUMBER!");
                    }
                }
                else if(command==4){
                    internalStage--;
                }
                else {
                    System.out.println("WRONG NUMBER!");
                }
            }
            if(internalStage==2){
                System.out.println("Choose number of the user:");
                int index = InputVerificator.intValueCheck(input.readLine());
                if(index < 0 || index > users.size()){
                    System.out.println("WRONG NUMBER!");
                }
                else {
                    user = ((User)users.toArray()[index-1]);
                    internalStage++;
                }
            }
            if(internalStage==3){
                System.out.println("Choose an option");
                System.out.println("[1]Change login;");
                System.out.println("[2]Change password;");
                System.out.println("[3]Change name;");
                System.out.println("[4]Change surname;");
                System.out.println("[5]Change surname;");
                System.out.println("[6]Change faculty;");
                System.out.println("[7]Back.");
                command = InputVerificator.intValueCheck(input.readLine());
                if(command==1){
                    System.out.println("Enter new login for use:");
                    user.name = input.readLine();
                }
                else if(command==2){
                    System.out.println("Enter new login for use:");
                    user.name = input.readLine();
                }
                else if(command==3){
                    System.out.println("Enter new name for use:");
                    user.name = input.readLine();
                    teacherWasEdited = true;
                }
                else if(command==4){
                    System.out.println("Enter new surname for use:");
                    user.name = input.readLine();
                    teacherWasEdited = true;
                }
                else if(command==5){
                    System.out.println("Choose role for user:");
                    System.out.println("[1]STUDENT;\n[2]TEACHER;\n[3]MANAGER;\n[4]ADMIN;\n[5]SYSTEM;\n[6]LIBRARIAN.");
                    int index = InputVerificator.intValueCheck(input.readLine());
                    if(index < 1 || index > 6){
                        System.out.println("WRONG NUMBER!");
                    }
                    else {
                        user.role = Role.values()[index-1];
                        teacherWasEdited = true;
                        studentWasEdited = true;
                    }
                }
                else if(command==6){
                    System.out.println("Choose a faculty:");
                    System.out.println("[1]FIT;\n[2]MCM;\n[3]BS;\n[4]ISE;\n[5]KMA;\n[6]FEOGI;\n[7]SCE.");
                    int index = InputVerificator.intValueCheck(input.readLine());
                    if(index < 1 || index > 7){
                        System.out.println("Вы ввели не корректный номер факультета!");
                    }
                    else {
                        user.faculty = Faculty.values()[index-1];
                        teacherWasEdited = true;
                    }
                }
                else if(command==7){
                    if(user.role==Role.TEACHER && teacherWasEdited){
                        Teacher teacher = (Teacher)user;
                        if(teacher.courses.size()>0){
                            for(String courseId : teacher.courses){
                                Course course = Intronet.getInstance().getCourseById(courseId);
                                if(teacher.faculty==course.faculty){
                                    for (Lesson lesson : course.lessons){
                                        course.schedule.updateLessonName(lesson);
                                    }
                                }
                                else {
                                    Intronet.getInstance().dropTeacherFromCourse(course,teacher);
                                }
                            }
                        }
                    }
                    else if(user.role==Role.STUDENT && studentWasEdited){
                        Student student = (Student) user;
                        if(student.courses.size()>0){
                            for(String courseId : student.courses.keySet()){
                                Course course = Intronet.getInstance().getCourseById(courseId);
                                if(student.faculty!=course.faculty){
                                    Intronet.dropStudentFromCourse(student,course);
                                }
                            }
                        }
                    }
                    internalStage=1;
                    user=null;
                    start=false;
                    teacherWasEdited = false;
                    studentWasEdited = false;
                }
                else {
                    System.out.println("WRONG NUMBER!");
                }

            }
            if (internalStage==4){
                UserConstructor.userCreation(input);
                internalStage=0;
            }
        }
    }
}

