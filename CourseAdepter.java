import java.util.*;
public class CourseAdepter {
    public static Course[] getCourses(Vector<Course> allCourses, Student student) {
        List<Course> courses = new ArrayList<>();
        for (Course course : allCourses) {
            boolean enable = true;
            if (student.department != course.department) {
                enable = false;
            }
            int prerecs = course.prerequisites.size();
            for (Course passedCourse : student.passedCourse.values()) {
                if (passedCourse.equals(course)) {
                    prerecs--;
                }
            }
            if (prerecs != 0) {
                enable = false;
            }
            if (enable) {
                courses.add(course);
            }
        }
        return courses.toArray(new Course[courses.size()]);
    }
    public static Request[] getRequests(Manager manager) {
        List<Request> requests = new ArrayList<>();
        for (Request request : Intronet.requests) {
            if (request.department == manager.department) {
                requests.add(request);
            }
        }
        return requests.toArray(new Request[requests.size()]);
    }
    public static <T> T[] toArray(Collection<T> collection) {
        T[] array = (T[]) new Object[collection.size()];
        return collection.toArray(array);
    }
    public static <T> T getObjectFromArray(T[] array, int index) {
        if (array == null || index < 0 || index >= array.length) {
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
