import Enums.Department;

import java.util.HashSet;

public class Discipline {
    Department department;
    HashSet<Course> prerequisites;
    String title;
    String description;
    int credits;
    {prerequisites = new HashSet<>();}
    public Discipline(Department department, String title, String description, int credits) {
        this.department = department;
        this.title = title;
        this.description = description;
        this.credits = credits;
    }

}
