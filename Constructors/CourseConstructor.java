package Constructors;

import Core.Course;
import Enums.Faculty;

import java.util.Scanner;

//public Course(Faculty faculty, String title, String description, int credits, int capacity, String code)
public class CourseConstructor {
    Scanner input = new Scanner(System.in);
    public Course courseCreation(){
        Faculty faculty = null;
        String title = null;
        String description = null;
        int credits = 0;
        int capacity = 0;
        String code = null;

        System.out.println("Выберите факльтет курса:");
        System.out.println("[1]FIT;\n[2]MCM;\n[3]BS;\n[4]ISE;\n[5]KMA;\n[6]FEOGI;\n[7]SCE.");

        while (faculty == null){
            int index = input.nextInt();
            if(index < 0 || index > 8){
                System.out.println("Вы ввели не корректный номер факультета!");
            }
            else {
                faculty = Faculty.values()[index];
            }
        }
        while (title==null){
            System.out.println("Ввудите название курса");
            title = input.next();
            title.
        }

    }
}

