package Constructors;

import Core.InputVerification;
import Core.Intranet;
import Core.Logs;
import Enums.Degree;
import Enums.Faculty;
import Enums.Role;
import Frontend.SchduleDrawer;
import Users.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Vector;
/**
 * This class is for user creation
 *
 * @author Eduardo
 */
public class UserConstructor {
    /**
     *
     * @param input buffered reader for input data
     * @param users vector of users in the system
     * @throws IOException
     */
    public static void userCreation(BufferedReader input, Vector<User> users) throws IOException {
        User user = null;
        String login = null;
        String password = null;
        String name = null;
        String surname = null;
        Role role = null;
        Faculty faculty = null;
        Degree degree = null;
        int internalStage=0;
        boolean inProgress = true;
        boolean inEdit = false;
        while (inProgress){
            if(internalStage==0){
                login=null;
                while (login==null){
                    System.out.println("Enter user login");
                    login = input.readLine();
                    if(!inEdit){
                        internalStage++;
                    }
                    else {
                        user.setLogin(login);
                        internalStage=7;
                    }
                }
            }
            if(internalStage==1){
                password=null;
                while (password==null){
                    System.out.println("Enter user password");
                    password = input.readLine();
                    if(!inEdit){
                        internalStage++;
                    }
                    else {
                        user.setPassword(password);
                        internalStage=7;
                    }
                }
            }
            if(internalStage==2){
                name=null;
                while (name==null){
                    System.out.println("Enter user name");
                    name = input.readLine();
                    if(!inEdit){
                        internalStage++;
                    }
                    else {
                        user.setName(name);
                        internalStage=7;
                    }
                }
            }
            if(internalStage==3){
                surname=null;
                while (surname==null){
                    System.out.println("Enter user surname");
                    surname = input.readLine();
                    if(!inEdit){
                        internalStage++;
                    }
                    else {
                        user.setSurname(surname);
                        internalStage=7;
                    }
                }
            }
            if(internalStage==4){
                role=null;
                while (role==null){
                    System.out.println("Choose a role for user:");
                    System.out.println("[1]STUDENT;\n[2]TEACHER;\n[3]MANAGER;\n[4]ADMIN;\n[5]SYSTEM;\n[6]LIBRARIAN.");
                    int index = InputVerification.intValueCheck(input.readLine());
                    if(index < 0 || index > 6){
                        System.out.println("Wrong number was entered!");
                    }
                    else {
                        role = Role.values()[index-1];
                        if(!inEdit){
                            internalStage++;
                        }
                        else {
                            internalStage=6;
                        }
                    }
                }
            }
            if(internalStage==5){
                faculty=null;
                while (faculty == null){
                    System.out.println("Choose a faculty for user:");
                    System.out.println("[1]FIT;\n[2]MCM;\n[3]BS;\n[4]ISE;\n[5]KMA;\n[6]FEOGI;\n[7]SCE.");
                    int index = InputVerification.intValueCheck(input.readLine());
                    if(index < 0 || index > 7){
                        System.out.println("Wrong number was entered!");
                    }
                    else {
                        faculty = Faculty.values()[index-1];
                        if(!inEdit){
                            internalStage++;
                        }
                        else {
                            user.setFaculty(faculty);
                            internalStage=7;
                        }
                    }
                }
            }
            if(internalStage==6){
                if(role == Role.ADMIN){
                    user=new Admin(login,password,name,surname,role,faculty);
                    internalStage++;
                }
                else if(role == Role.MANAGER){
                    user = new Manager(login,password,name,surname,role,faculty);
                    internalStage++;
                }
                else if(role == Role.TEACHER){
                    if(!inEdit){
                        degree=null;
                        while (degree==null){
                            System.out.println("Choose a degree for user:");
                            System.out.println("[1]Master;\n[2]PHD");
                            int index = InputVerification.intValueCheck(input.readLine());
                            if(index < 0 || index > 2){
                                System.out.println("Wrong number was entered!");
                            }
                            else {
                                degree = Degree.values()[index];
                                user = new Teacher(login,password,name,surname,role,faculty,degree);
                                internalStage++;
                            }
                        }
                    }
                    else {
                        user = new Teacher(login,password,name,surname,role,faculty,degree);
                        internalStage++;
                    }
                }
                else if (role == Role.STUDENT){
                    if(!inEdit){
                        degree=null;
                        while (degree==null){
                            System.out.println("Choose a degree for user:");
                            System.out.println("[1]Bachelor;\n[2]Master;\n[3]PHD");
                            int index = InputVerification.intValueCheck(input.readLine());
                            if(index < 0 || index > 3){
                                System.out.println("Wrong number was entered!");
                            }
                            else {
                                degree = Degree.values()[index-1];
                                user=new Student(login,password,name,surname,role,faculty,degree);
                                internalStage++;
                            }
                        }
                    }
                    else {
                        user=new Student(login,password,name,surname,role,faculty,degree);
                        internalStage++;
                    }
                }
            }
            if(internalStage==7){
                System.out.println(user.getClass().getName());
                user.setId("------");
                SchduleDrawer.printUserInfo(user,0,0,(user.toString()).length(),Math.max(user.getLogin().length(),"Login".length()),Math.max(user.getPassword().length(),"Password".length()),true);
                System.out.println("Is information correct?");
                System.out.println("[1]Yes");
                System.out.println("[2]No");
                int command = InputVerification.intValueCheck(input.readLine());
                if(command==1){
                    user.setId(Intranet.getInstance().generateUserId());
                    users.add(user);
                    inProgress=false;
                    inEdit=false;
                    Logs.AddToLog("User: " + user.toString() + " was added to system");
                }
                else if(command==2){
                    inEdit=true;
                    internalStage++;
                }
                else {
                    System.out.println("You enter wrong number!");
                }
            }
            if(internalStage==8){
                System.out.println("Which field do you want to change");
                System.out.println("[1]User login");
                System.out.println("[2]User password");
                System.out.println("[3]User name");
                System.out.println("[4]User surname");
                System.out.println("[5]User role");
                System.out.println("[6]User faculty");
                if(user.getRole()==Role.STUDENT || user.getRole()==Role.TEACHER){
                    System.out.println("[7]User degree");
                }
                int command = InputVerification.intValueCheck(input.readLine());
                if(command>7 && (user.getRole()==Role.STUDENT || user.getRole()==Role.TEACHER)){
                    System.out.println("Wrong number!");
                }
                else if(!(user.getRole()==Role.STUDENT || user.getRole()==Role.TEACHER)&& command>6){
                    System.out.println("Wrong number!");
                }
                else{
                    internalStage=command-1;
                }
            }


        }

    }
}
