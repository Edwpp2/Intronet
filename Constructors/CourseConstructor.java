package Constructors;

import Core.Course;
import Core.InputVerification;
import Core.Intranet;
import Enums.Faculty;
import Frontend.SchduleDrawer;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Class for course creation
 *
 * @author Eduardo
 */
public class CourseConstructor {

    /**
     * Function for course creation
     * @param input buffered reader to get input data
     * @throws IOException
     */
    public static void courseCreation(BufferedReader input) throws IOException {
        Course course = null;
        Faculty faculty = null;
        String title = null;
        String description = null;
        String code;
        int credits = 0;
        int capacity = 0;
        int internalStage = 0;

        boolean inProcessing = true;
        boolean inEdit = false;
        int command = 0;

        while (inProcessing){
            if(internalStage==0){
                faculty=null;
                while (faculty == null){
                    System.out.println("Enter faculty:");
                    System.out.println("[1]FIT;\n[2]MCM;\n[3]BS;\n[4]ISE;\n[5]KMA;\n[6]FEOGI;\n[7]SCE.");
                    int index = InputVerification.intValueCheck(input.readLine());
                    if(index < 0 || index > 7){
                        System.out.println("Wrong number!");
                    }
                    else {
                        faculty = Faculty.values()[index-1];
                        if(!inEdit){
                            internalStage++;
                        }
                        else {
                            internalStage=6;
                        }
                    }
                }
            }
            if(internalStage==1){
                title=null;
                while (title==null){
                    System.out.println("Enter course name");
                    title = input.readLine();
                    if(!inEdit){
                        internalStage++;
                    }
                    else {
                        internalStage=6;
                    }
                }
            }
            if(internalStage==2){
                description=null;
                while (description==null){
                    System.out.println("Enter course description");
                    description = input.readLine();
                    if(!inEdit){
                        internalStage++;
                    }
                    else {
                        internalStage=6;
                    }
                }
            }
            if(internalStage==3){
                credits=0;
                while (credits==0){
                    System.out.println("Enter amount of credits for course");
                    credits= InputVerification.intValueCheck(input.readLine());
                    if(!inEdit){
                        internalStage++;
                    }
                    else {
                        internalStage=6;
                    }
                }
            }
            if(internalStage==4){
                capacity=0;
                while (capacity==0){
                    System.out.println("Enter course capacity");
                    capacity= InputVerification.intValueCheck(input.readLine());
                    if(!inEdit){
                        internalStage++;
                    }
                    else {
                        internalStage=6;
                    }
                }
            }
            if(internalStage==5){
                code=null;
                while (code==null){
                    System.out.println("Enter code of course");
                    code=input.readLine();
                }
                if(!inEdit){
                    course =new Course(faculty,title,description,credits,capacity,code);
                    course.setId("------");
                }
                else {
                    course.faculty=faculty;
                    course.title = title;
                    course.description = description;
                    course.credits = credits;
                    course.capacity = capacity;
                    course.code = code;
                }
                internalStage++;
            }
            if(internalStage==6){
                SchduleDrawer.printInfoAboutCourse(course,course.name.length(),course.code.length(),0,0,true);
                System.out.println("Everything correct?");
                System.out.println("[1]Yes");
                System.out.println("[2]No");
                command= InputVerification.intValueCheck(input.readLine());
                if(command==1){
                    inEdit=false;
                    inProcessing=false;
                    Intranet.getInstance().addCourseToSystem(course);
                }
                else if(command==2){
                    inEdit=true;
                    internalStage=8;
                }
                else {
                    System.out.println("Wrong number!");
                }

            }
            if(internalStage==8){
                System.out.println("Choose an option to change:");
                System.out.println("[1]Change faculty;");
                System.out.println("[2]Change title;");
                System.out.println("[3]Change description;");
                System.out.println("[4]Change credits;");
                System.out.println("[5]Change capacity;");
                System.out.println("[6]Change code.");
                command = InputVerification.intValueCheck(input.readLine());
                if(command==1){
                    internalStage=command-1;
                }
                if(command==2){
                    internalStage=command-1;
                }
                if(command==3){
                    internalStage=command-1;
                }
                if(command==4){
                    internalStage=command-1;
                }
                if(command==5){
                    internalStage=command-1;
                }
                if(command==6){
                    internalStage=command-1;
                }
                else {
                    System.out.println("Wrong number!");
                }
            }
        }
    }
}

