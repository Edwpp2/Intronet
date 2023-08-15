package Comporators;

import Users.User;
import java.util.Comparator;

public class IdComporator implements Comparator<User> {

    @Override
    public int compare(User user1, User user2) {
        String id1 = user1.getId();
        String id2 = user2.getId();
        int num1 = Integer.parseInt(id1.replaceAll("[^0-9]", ""));
        int num2 = Integer.parseInt(id2.replaceAll("[^0-9]", ""));
        return Integer.compare(num2, num1);
    }
}
