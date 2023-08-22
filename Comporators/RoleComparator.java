package Comporators;

import Users.User;
import java.util.Comparator;

public class RoleComparator implements Comparator<User> {
    @Override
    public int compare(User user1, User user2) {
        return Integer.compare(user2.getRole().ordinal(), user1.getRole().ordinal());
    }
}
