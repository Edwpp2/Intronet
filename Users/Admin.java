package Users;

import Enums.Faculty;
import Enums.Role;
import Users.User;

public class Admin extends User {
    public Admin(String login, String password, String name, String surname, String id, Role role, Faculty faculty) {
        super(login, password, name, surname, id, role, faculty);
    }
}
