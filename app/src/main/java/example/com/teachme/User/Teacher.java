package example.com.teachme.User;

import java.util.Set;

import example.com.teachme.Course.Course;


public class Teacher extends User {


    private Set<Course> courses;

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public Teacher() {
        super();
    }

    public Teacher(Set<Course> courses) {
        super();
        this.courses = courses;
    }


}
