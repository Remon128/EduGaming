package example.com.teachme.model;

import java.util.List;


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
