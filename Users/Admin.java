package Users;

import Enums.Faculty;
import Enums.Role;

public class Admin extends User {
    public Admin(String login, String password, String name, String surname, Role role, Faculty faculty) {
        super(login, password, name, surname,role, faculty);
    }
}
