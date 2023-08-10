package Core;

import Core.Course;
import Enums.Faculty;

import java.io.Serializable;
import java.util.HashSet;

public class Discipline implements Serializable {
    public Faculty faculty;
    public String title;
    public String description;

    public String code;
    int credits;
    public Discipline(Faculty faculty, String title, String description, int credits,String code) {
        this.faculty = faculty;
        this.title = title;
        this.description = description;
        this.credits = credits;
        this.code = code;
    }

}
