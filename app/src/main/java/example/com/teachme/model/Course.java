package example.com.teachme.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Set;

public class Course {

    private Integer id;
    private String name;
    private String description;

    private Teacher teacher;

    private Set<Student> students;

    public Set<Student> getStudents() {
        return students;
    }

    public void putStudent(Student s) {
        this.students.add(s);
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }


    //	public Set<Game> getGames() {
//		return games;
//	}
//
    public Teacher getTeacher() {
        return teacher;
    }

    //
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
//
//	public void setGames(Set<Game> games) {
//		this.games = games;
//	}

    public Course() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
