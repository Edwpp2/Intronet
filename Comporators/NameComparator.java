package Comporators;

import Users.User;

import java.util.Comparator;

public class NameComparator implements Comparator<User> {

    @Override
    public int compare(User user1, User user2) {
        if(user1.name.compareTo(user2.name)==0){
            return user1.surname.compareTo(user2.surname);
        }
        return user1.name.compareTo(user2.name);
    }
}
