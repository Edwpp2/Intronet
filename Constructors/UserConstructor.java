package Constructors;

import Core.InputVerification;
import Core.Intranet;
import Enums.Degree;
import Enums.Faculty;
import Enums.Role;
import Frontend.SchduleDrawer;
import Users.*;
import java.io.BufferedReader;
import java.io.IOException;

public class UserConstructor {
    public static void userCreation(BufferedReader input) throws IOException {
        User user = null;
        String login = null;
        String password = null;
        String name = null;
        String surname = null;
        Role role = null;
        Faculty faculty = null;
        Degree degree;
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
                        user.login=login;
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
                        user.password=password;
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
                        user.name=name;
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
                        user.surname=surname;
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
                            user.role = role;
                            internalStage=7;
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
                            user.faculty=faculty;
                            internalStage=7;
                        }
                    }
                }
            }
            if(internalStage==6){
                if(role == Role.ADMIN){
                    if(!inEdit){
                        user=new Admin(login,password,name,surname,role,faculty);
                        internalStage++;
                    }
                    else {
                        internalStage++;
                    }
                }
                else if (role == Role.STUDENT){
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
                            if(!inEdit){
                                user=new Student(login,password,name,surname,role,faculty,degree);
                            }
                            else {
                                ((Student) user).degree = Degree.values()[index-1];
                            }
                            internalStage++;
                        }
                    }

                }
                else if(role == Role.MANAGER){
                    user = new Manager(login,password,name,surname,role,faculty);
                    internalStage++;
                }
                else if(role == Role.TEACHER){
                    degree=null;
                    while (degree==null){
                        System.out.println("Choose a degree for user:");
                        System.out.println("[1]Master;\n[2]PHD");
                        int index = InputVerification.intValueCheck(input.readLine());
                        if(index < 0 || index > 2){
                            System.out.println("Wrong number was entered!");
                        }
                        else {
                            degree = Degree.values()[index-1];
                            if(!inEdit){

                                user = new Teacher(login,password,name,surname,role,faculty,degree);
                            }
                            else {
                                ((Teacher) user).degree = Degree.values()[index-1];
                            }
                            internalStage++;
                        }
                    }
                }
            }
            if(internalStage==7){
                System.out.println(user.getClass().getName());
                user.setId("------");
                SchduleDrawer.printInfoAboutUser(user);
                System.out.println("Is information correct?");
                System.out.println("[1]Yes");
                System.out.println("[2]No");
                int command = InputVerification.intValueCheck(input.readLine());
                if(command==1){
                    Intranet.getInstance().addUserToSystem(user);
                    inProgress=false;
                    inEdit=false;
                }
                if(command==2){
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
                if(user.role==Role.STUDENT || user.role==Role.TEACHER){
                    System.out.println("[7]User degree");
                }
                int command = InputVerification.intValueCheck(input.readLine());
                if(command>7 && (user.role==Role.STUDENT || user.role==Role.TEACHER)){
                    System.out.println("Wrong number!");
                }
                else if(!(user.role==Role.STUDENT || user.role==Role.TEACHER)&& command>6){
                    System.out.println("Wrong number!");
                }
                else{
                    internalStage=command-1;
                }
            }


        }
//        System.out.println(user.getClass().getName());
//        user.setId(Intranet.generateUserId());
//        SchduleDrawer.printInfoAboutUser(user);
    }
}
