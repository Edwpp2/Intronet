package Comporators;

import Users.User;

import java.util.Comparator;

public class NameComparator implements Comparator<User> {

    @Override
    public int compare(User user1, User user2) {
        if(user1.getName().compareTo(user2.getName())==0){
            return user1.getName().compareTo(user2.getName());
        }
        return user1.getName().compareTo(user2.getName());
    }
}
