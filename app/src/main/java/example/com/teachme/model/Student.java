package example.com.teachme.model;


import java.util.Set;

import example.com.teachme.model.Game;
import example.com.teachme.model.User;

public class Student extends User {
    Set<Course> courses;


    public Set<Course> getCourse() {
        return courses;
    }

    public void setCourse(Set<Course> course)
    {
        this.courses = course;
    }

    public Student() {
        super();
    }

    public Student(String name, String mail, String password) {
        super(name, mail, password);
    }
}
