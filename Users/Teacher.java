package Users;

import Core.*;
import Enums.Degree;
import Enums.Faculty;
import Enums.Role;
import Frontend.SchduleDrawer;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.Serializable;

import java.util.HashMap;
import java.util.HashSet;

public class Teacher extends User implements Serializable,StudyPerson{
    public HashSet<String> courses;
    public Schedule schedule;
    public Degree degree;
    public Teacher(String login, String password, String name, String surname, Role role, Faculty faculty,Degree degree) {
        super(login, password, name, surname, role, faculty);
        this.degree = degree;
        this.schedule = new Schedule();
        this.courses = new HashSet<>();
    }
    public Schedule getSchedule() {
        return this.schedule;
    }
    @Override
    public void dropCourse(Course course) {
        Intranet.getInstance().dropTeacherFromCourse(course, this);
    }
    @Override
    public void addCourse(Course course) {
        Intranet.getInstance().addTeacherToCourse(course, this);
    }
    @Override
    public Degree getDegree() {
        return degree;
    }

    public void addMaterial(Course course,String filename){

        course.materials.add((new Material(filename)));
        Logs.AddToLog(this + " add material to" + course.name);
    }
    public void removeMaterial(Course course, int materialNum){
        try {
            course.materials.remove(materialNum - 1);
            Logs.AddToLog(this + " remove material from" + course.name);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Wrong number!");
        }
    }
    public Student chooseStudent(Course course,int studentIndex) {
        try {
            return (Student) Intranet.getInstance().getUserById((String) course.studentMarks.keySet().toArray()[studentIndex - 1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("WRONG NUMBER!");
        }
        return null;
    }
    public void finishCourse(Course course){
        for (String id : course.studentMarks.keySet()) {
            Student passedStudent = (Student) Intranet.getInstance().getUserById(id);
            Mark mark = course.studentMarks.get(id);
            if (!mark.finalHeld) {
                System.out.println("Some students has no points for final!");
            } else {
                if (mark.isRetake()) {
                    Logs.AddToLog("Student: " + Intranet.getInstance().getUserById(id).toString() + " fail" + course.name);
                } else {
                    Logs.AddToLog("Student: " + Intranet.getInstance().getUserById(id).toString() + " pass" + course.name);
                    passedStudent.passedCoursesCnt++;
                    passedStudent.transcript.computeIfAbsent(passedStudent.yearOfStudy, k -> new HashMap<>());
                    HashMap<String, Mark> passedCourseAndMarks = passedStudent.transcript.get(passedStudent.yearOfStudy);
                    passedCourseAndMarks.put(course.getId(), course.studentMarks.get(passedStudent.getId()));
                    passedStudent.nextCourse();
                }
            }
            Intranet.dropStudentFromCourse(passedStudent, course);
        }
    }
    public Course chooseCourse(int courseNum) throws IOException {
        try {
            return Intranet.getInstance().getCourseById((String) this.courses.toArray()[courseNum-1]);
        }
        catch (NumberFormatException a) {
            System.out.println("Value that was entered is't number!");
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Wrong number!");
        }
        return null;
    }
    public void putMarkForFirstAtt(double mark,Student student,Course course){
        if (mark < 0 || mark > 60) {
            System.out.println("WRONG MARK!");
        } else {
            student.courses.get(course.getId()).putPointForFirstAtt(mark);
            course.studentMarks.get(student.getId()).putPointForFirstAtt(mark);
            Logs.AddToLog(this + " put" + mark + " for first att on" + course.name);
        }
    }
    public void putMarkForSecondAtt(double mark,Student student,Course course){
        if (mark < 0 || mark > 60) {
            System.out.println("WRONG MARK!");
        }
        else {
            student.courses.get(course.getId()).putPointForSecondAtt(mark);
            course.studentMarks.get(student.getId()).putPointForSecondAtt(mark);
            Logs.AddToLog(this + " put" + mark + " for second att on" + course.name);
        }
    }
    public void putMarkForFinal(double mark,Student student,Course course) {
        if (mark < 0 || mark > 100) {
            System.out.println("WRONG MARK!");
        } else {
            student.courses.get(course.getId()).putPointsForFinal(mark);
            course.studentMarks.get(student.getId()).putPointsForFinal(mark);
            Logs.AddToLog(this + "put" + mark + " for final on" + course.name);
        }
    }
    public void putAbsence(Student student,Course course) {
        student.courses.get(course.getId()).putAcscenseCount();
        course.studentMarks.get(student.getId()).putAcscenseCount();
        Logs.AddToLog(this + "put absence");

    }
}
