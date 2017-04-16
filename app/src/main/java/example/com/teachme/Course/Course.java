package example.com.teachme.Course;

import java.util.Set;

import example.com.teachme.Game.Game;
import example.com.teachme.User.Teacher;

public class Course {
	
	private String id;
	
	private String name;
	private String description;
	
	private Teacher teacher;

	private Set<Game> games;
	
	public Set<Game> getGames() {
		return games;
	}

	public void setGames(Set<Game> games) {
		this.games = games;
	}

	public Course() {
		
	}
	
	public Course(String id, String name, String description, Teacher teacher) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.teacher = teacher;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	
}
