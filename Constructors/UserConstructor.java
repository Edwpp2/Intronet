package Constructors;

import Core.Intronet;
import Enums.Degree;
import Enums.Faculty;
import Enums.Role;
import Users.*;
import java.util.Scanner;
public class UserConstructor {
    public static void userCreation(){
        String login = null;
        String password = null;
        String name = null;
        String surname = null;
        Role role = null;
        Faculty faculty = null;
        Degree degree = null;
        Scanner input = new Scanner(System.in);
        while (login==null){
            System.out.println("Введите логин пользователя");
            login = input.next();
        }
        while (password==null){
            System.out.println("Введите пароль пользователя");
            password = input.next();
        }
        while (name==null){
            System.out.println("Введите имя пользователя");
            name = input.next();
        }
        while (surname==null){
            System.out.println("Введите фамилию пользователя");
            surname = input.next();
        }
        while (role==null){
            System.out.println("Выберите роль пользователя:");
            System.out.println("[1]STUDENT;\n[2]TEACHER;\n[3]MANAGER;\n[4]ADMIN;\n[5]SYSTEM;\n[6]LIBRARIAN.");
            int index = input.nextInt();
            if(index < 0 || index > 6){
                System.out.println("Вы ввели не корректный номер!");
            }
            else {
                role = Role.values()[index-1];
            }
        }
        while (faculty == null){
            System.out.println("Выберите факльтет курса:");
            System.out.println("[1]FIT;\n[2]MCM;\n[3]BS;\n[4]ISE;\n[5]KMA;\n[6]FEOGI;\n[7]SCE.");
            int index = input.nextInt();
            if(index < 0 || index > 7){
                System.out.println("Вы ввели не корректный номер факультета!");
            }
            else {
                faculty = Faculty.values()[index-1];
            }
        }
        if(role == Role.ADMIN){
            Intronet.users.add(new Admin(login,password,name,surname,role,faculty));
        }
        else if (role == Role.STUDENT){
            while (degree==null){
                System.out.println("Выберите ученую степень:");
                System.out.println("[1]Bachelor;\n[2]Master;\n[3]PHD");
                int index = input.nextInt();
                if(index < 0 || index > 3){
                    System.out.println("Вы ввели не корректный номер факультета!");
                }
                else {
                    degree = Degree.values()[index-1];
                }
            }
            Intronet.users.add(new Student(login,password,name,surname,role,faculty,degree));
        }
        else if(role == Role.MANAGER){
            Intronet.users.add(new Manager(login,password,name,surname,role,faculty));
        }
        else if(role == Role.TEACHER){
            while (degree==null){
                System.out.println("Выберите ученую степень:");
                System.out.println("[1]Master;\n[2]PHD");
                int index = input.nextInt();
                if(index < 0 || index > 2){
                    System.out.println("Вы ввели не корректный номер факультета!");
                }
                else {
                    degree = Degree.values()[index-1];
                }
            }
            Intronet.users.add(new Teacher(login,password,name,surname,role,faculty,degree));
        }
    }
}
