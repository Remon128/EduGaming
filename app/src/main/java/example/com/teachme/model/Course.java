package example.com.teachme.model;

import java.util.HashSet;
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
        if(students==null)
            this.students = new HashSet<Student>();
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
       students = new HashSet<Student>();
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
