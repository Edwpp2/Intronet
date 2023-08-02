package Users;
import Core.Schedule;
import Enums.Faculty;
import Enums.Role;

import java.util.HashMap;
import java.util.HashSet;

public class Teacher extends User {
    public HashMap<String,HashMap<String,Double>> ratingByCourse;
    public HashSet<String> courses;
    Schedule schedule;

    public Teacher(String login, String password, String name, String surname, String id, Role role, Faculty faculty) {
        super(login, password, name, surname, role, faculty);
        this.faculty = faculty;
        this.schedule = new Schedule();
        this.courses = new HashSet<>();
    }
    public Schedule getSchedule() {
        return this.schedule;
    }






}
