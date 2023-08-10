package Frontend;

import Comporators.IdComporator;
import Constructors.UserConstructor;
import Core.Intronet;
import Enums.Faculty;
import Enums.Role;
import Users.Manager;
import Users.User;

import java.util.Collections;
import java.util.Scanner;
import java.util.Vector;

public class UserManagmentGUI {
    public void menu(Manager manager, User user, Scanner input, boolean start){
        int internalStage = 0;
        int command = 0;
        Vector<User> users = Intronet.users;
        while (start){
            if(internalStage==0){
                System.out.println("Выебрите опцию");
                System.out.println("[1]View all users");
                System.out.println("[2]Back");
                command = input.nextInt();
                if(command==1){
                    internalStage++;
                }
                if(command==2){
                    start=false;
                    user = null;
                    internalStage=0;
                    Intronet.users=users;
                }
            }
            if(internalStage==1){
                SchduleDrawer.printUsersForSystem(users);
                System.out.println("Выберите опцию:");
                System.out.println("[1]Edit user;");
                System.out.println("[2]Add user");
                System.out.println("[3]Sort user");
                System.out.println("[4]Back");
                command = input.nextInt();
                if(command==1){
                    internalStage++;
                }
                if(command==2){
                    internalStage=3;
                }
                if(command==3){
                    System.out.println("How do you wont to sort users:");
                    System.out.println("[1]By id;");
                    System.out.println("[2]By name and surname;");
                    System.out.println("[3]By faculty;");
                    System.out.println("[4]By role;");
                    System.out.println("[4]Dont change.");
                    command=input.nextInt();
                    if(command==1){
                        Collections.sort(users, IdComporator);
                    }
                    if(command==2){

                    }
                    if(command==3){

                    }
                    if(command==4){

                    }
                    if(command==5){

                    }
                }
                if(command==4){
                    internalStage--;
                }
            }
            if(internalStage==2){
                System.out.println("Выберите номер пользователя:");
                int index = input.nextInt();
                user = (User) users.toArray()[index-1];
                if(user!=null){
                    internalStage++;
                }
                else {
                    System.out.println("Error");
                }
            }
            if(internalStage==3){
                System.out.println("Выберите опцию:");
                System.out.println("[1]Change login;");
                System.out.println("[2]Change password;");
                System.out.println("[3]Change name;");
                System.out.println("[4]Change name;");
                System.out.println("[5]Change surname;");
                System.out.println("[6]Change faculty;");
                System.out.println("[7]Back.");
                if(command==1){
                    System.out.println("Enter new login for use:");
                    user.name = input.next();
                }
                if(command==2){
                    System.out.println("Enter new login for use:");
                    user.name = input.next();
                }
                if(command==3){
                    System.out.println("Enter new name for use:");
                    user.name = input.next();
                }
                if(command==4){
                    System.out.println("Enter new surname for use:");
                    user.name = input.next();
                }
                if(command==5){
                    System.out.println("Выберите роль пользователя:");
                    System.out.println("[1]STUDENT;\n[2]TEACHER;\n[3]MANAGER;\n[4]ADMIN;\n[5]SYSTEM;\n[6]LIBRARIAN.");
                    int index = input.nextInt();
                    if(index < 0 || index > 6){
                        System.out.println("Вы ввели не корректный номер!");
                    }
                    else {
                        user.role = Role.values()[index-1];
                    }
                }
                if(command==6){
                    System.out.println("Выберите факльтет курса:");
                    System.out.println("[1]FIT;\n[2]MCM;\n[3]BS;\n[4]ISE;\n[5]KMA;\n[6]FEOGI;\n[7]SCE.");
                    int index = input.nextInt();
                    if(index < 0 || index > 7){
                        System.out.println("Вы ввели не корректный номер факультета!");
                    }
                    else {
                        user.faculty = Faculty.values()[index-1];
                    }
                }
                if(command==7){
                    internalStage--;
                }
            }
            if (internalStage==4){
                UserConstructor.userCreation();
                internalStage=0;
            }
        }
    }
}
