package Users;

import Core.Course;
import Enums.Degree;

public interface StudyPerson {
    public void dropCourse(Course course);
    public void addCourse(Course course);
    public Degree getDegree();
}
