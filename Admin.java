import Enums.Department;
import Enums.Role;

public class Admin extends User {
    public Admin(String login, String password, String name, String surname, String id, Role role, Department department) {
        super(login, password, name, surname, id, role, department);
    }
}
