package Core;

import Core.Course;
import Enums.Faculty;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;

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
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Discipline)) return false;
        Discipline that = (Discipline) o;
        return faculty == that.faculty && title.equals(that.title) && code.equals(that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(faculty, title, code);
    }
}
