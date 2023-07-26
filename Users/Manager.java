package Users;

import Enums.Faculty;
import Enums.Role;
import Users.User;

public class Manager extends User {
    public Manager(String login, String password, String name, String surname, String id, Role role, Faculty faculty) {
        super(login, password, name, surname,role, faculty);
        this.faculty = faculty;
    }


}
