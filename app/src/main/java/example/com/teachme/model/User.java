package example.com.teachme.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import com.google.gson.annotations.Expose;


import example.com.teachme.Course.Course;

public class User {

    @SerializedName("mail")
    @Expose
    private String mail;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("")
    @Expose
    private List<Course> courses = null;

    public User() {
        this.mail = "";
        this.name = "";
        this.password = "";
        this.courses = null;

    }

    public User(String mail, String name, String password, List<Course> courses) {
        this.mail = mail;
        this.name = name;
        this.password = password;
        this.courses = courses;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}