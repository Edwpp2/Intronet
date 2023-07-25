package Core;

import Core.Course;
import Enums.Faculty;

import java.util.HashSet;

public class Discipline {
    Faculty faculty;
    String title;
    String description;
    int credits;
    public Discipline(Faculty faculty, String title, String description, int credits) {
        this.faculty = faculty;
        this.title = title;
        this.description = description;
        this.credits = credits;
    }

}
