package Comporators;

import Users.User;

import java.util.Comparator;

public class FacultyComporator implements Comparator<User> {

    @Override
    public int compare(User user1, User user2) {
        return Integer.compare(user2.faculty.ordinal(), user1.faculty.ordinal());
    }
}
