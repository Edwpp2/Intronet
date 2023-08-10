package Core;

import Users.Manager;
import Users.Student;
import Core.*;
import java.util.*;
public class CourseAdepter {
    public static <T> T[] toArray(Collection<T> collection) {
        T[] array = (T[]) new Object[collection.size()];
        return collection.toArray(array);
    }
    public static <T> T getObjectFromArray(T[] array, int index) {
        if (array == null || index < 0 || index > array.length) {
            throw new IllegalArgumentException("Invalid array or index");
        }
        return array[index-1];
    }
    public static <T> void viewObjectsInArray(T[] array) {
        for(int i = 0; i < array.length;i++){
            System.out.println(i+1 + " " + array[i].toString());
        }
    }
}
