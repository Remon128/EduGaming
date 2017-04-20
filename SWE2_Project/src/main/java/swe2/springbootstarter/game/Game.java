package swe2.springbootstarter.game;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import swe2.springbootstarter.course.Course;
import swe2.springbootstarter.question.Question;

@Entity
public class Game {
	
	@Id
	private String name;
	private String description;
	private int score;
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@ManyToOne
	@JoinColumn
	private Course course;
	
	@OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
	private Set<Question> questions;
	
	
	public Set<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}

	public Game() {
		
	}
	
	public Game(String name, String description, String courseId, Course course) {
		super();
		this.name = name;
		this.description = description;
		this.course = course;
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
	
	@JsonIgnore
	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
}
