package example.com.teachme.User;

import java.util.List;
import java.util.Set;

import example.com.teachme.Course.Course;
import example.com.teachme.model.User;


public class Teacher extends User {


    private List<Course> courses;

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public Teacher() {
        super();
    }

    public Teacher(List<Course> courses) {
        super();
        this.courses = courses;
    }
}
