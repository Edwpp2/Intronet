package Constructors;

import Core.Course;
import Enums.Faculty;

import java.util.Scanner;

//public Course(Faculty faculty, String title, String description, int credits, int capacity, String code)
public class CourseConstructor {
    public Course courseCreation(){
        Faculty faculty = null;
        String title = null;
        String description = null;
        int credits = 0;
        int capacity = 0;
        String code = null;
        Scanner input = new Scanner(System.in);

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
        while (title==null){
            System.out.println("Ввудите название курса");
            title = input.next();
        }
        while (description==null){
            System.out.println("Введите описание к курсу");
            description = input.next();
        }
        while (credits==0){
            System.out.println("Введите кредитность курса");
            credits=input.nextInt();
        }
        while (capacity==0){
            System.out.println("Введите вметсимость курса");
            capacity=input.nextInt();
        }
        while (code==null){
            System.out.println("Введите вметсимость курса");
            code=input.next();
        }
        return new Course(faculty,title,description,credits,capacity,code);
    }
}

