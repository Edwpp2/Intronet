package Users;

import Enums.Faculty;
import Enums.Role;

public class Teacher extends User {
    public Teacher(String login, String password, String name, String surname, String id, Role role, Faculty faculty) {
        super(login, password, name, surname, id, role, faculty);
        this.faculty = faculty;
    }




}
