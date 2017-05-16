package example.com.teachme.model;


import java.util.Set;

import example.com.teachme.model.Game;
import example.com.teachme.model.User;

public class Student extends User {

    public Student() {
        super();
    }

    public Student(String name, String mail, String password) {
        super(name, mail, password);
    }
}
