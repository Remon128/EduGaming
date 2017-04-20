package swe2.springbootstarter.course;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import swe2.springbootstarter.game.Game;
import swe2.springbootstarter.user.Student;
import swe2.springbootstarter.user.Teacher;

@Entity
public class Course {
	
	@Id
	private String name;
	private String description;
	
	@ManyToOne
	@JoinColumn
	private Teacher teacher;
	
	@ManyToOne
	@JoinColumn
	private Student studnet;
	
	public Student getStudnet() {
		return studnet;
	}

	public void setStudnet(Student studnet) {
		this.studnet = studnet;
	}
	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
	private Set<Game> games;
	
	
	public Set<Game> getGames() {
		return games;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public void setGames(Set<Game> games) {
		this.games = games;
	}

	public Course() {
		
	}
	
	public Course(String name, String description, Teacher teacher) {
		super();
		this.name = name;
		this.description = description;
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

	

	
}
