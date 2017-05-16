package swe2.springbootstarter.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class Comment {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Integer id ;
	
	private String comment;
	
	@ManyToOne
	@JoinColumn
	private Game game;
	
//	public Game getGame() {
//		return game;
//	}



	public void setGame(Game game) {
		this.game = game;
	}

	@ManyToOne
	@JoinColumn
	private Teacher teacher;
	
	@ManyToOne
	@JoinColumn
	private Student student;
	
	
	
	public Comment() {
		
	}

	

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Comment(String comment, Game game) {
		super();
		this.comment = comment;
		this.game = game;
	}



//	public Teacher getTeacher() {
//		return teacher;
//	}



	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Student getStudent() {
		return student;
	}

//	public Student getStudent() {
//		return student;
//	}



	public void setStudent(Student student) {
		this.student = student;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}
