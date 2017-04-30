package swe2.springbootstarter.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Course {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String name;
	private String description;
	
	@ManyToOne
	@JoinColumn
	private Teacher teacher;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "course_student", joinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "student_mail", referencedColumnName = "mail"))
	private Set<Student> students;
	
	

	public Set<Student> getStudents() {
		return students;
	}
	public void putStudent(Student s){
		this.students.add(s);
	}
	public void setStudents(Set<Student> students) {
		this.students = students;
	}
	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
	
	private Set<Game> games;
	
	
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
		
		this.students = new HashSet<Student>();
		
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
