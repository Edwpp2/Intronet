package Users;

import Core.Schedule;
import Enums.Faculty;
import Enums.Role;

public class Teacher extends User {

    Schedule schedule;
    public Teacher(String login, String password, String name, String surname, String id, Role role, Faculty faculty) {
        super(login, password, name, surname, role, faculty);
        this.faculty = faculty;
        this.schedule = new Schedule();
    }
    public Schedule getSchedule() {
        return this.schedule;
    }





}
