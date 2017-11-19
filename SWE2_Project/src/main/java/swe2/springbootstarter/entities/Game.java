package swe2.springbootstarter.entities;

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
public class Game {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	private String name;
	private String description;
	private int score;
	private boolean available;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "game_teachers", joinColumns = @JoinColumn(name = "game_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "teacher_mail", referencedColumnName = "mail"))      
	private Set<Teacher> collaborators;	
	
	
	@OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
	private Set<Comment> comments;
	
	

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}



	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}
	
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
	

	public Game() {
		
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
	

	public void setCourse(Course course) {
		this.course = course;
	}
	
	public boolean isAvailable() {                          
		return available;
	}

	public void setAvailable(boolean available) {		
		this.available = available;
	}
}
