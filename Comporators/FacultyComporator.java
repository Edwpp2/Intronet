package Comporators;

import Users.User;

import java.util.Comparator;

public class FacultyComporator implements Comparator<User> {

    @Override
    public int compare(User user1, User user2) {
        return Integer.compare(user2.getFaculty().ordinal(), user1.getFaculty().ordinal());
    }
}
