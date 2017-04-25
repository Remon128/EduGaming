package example.com.teachme.model;

import java.util.Set;

import example.com.teachme.Question.Question;

public class Game {
	
	private String id;
	private String name;
	private String description;
	private int score;
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	private Course course;
	
	private Set<Question> questions;
	
	public Set<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}

	public Game() {
		
	}
	
	public Game(String id, String name, String description, String courseId, Course course) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.course = course;
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

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
}
