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
	private String mail;
	
	@ManyToOne
	@JoinColumn
	private Game game;
	
		
	
	public Comment() {
		
	}



	public void setGame(Game game) {
		this.game = game;
	}



	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}



	public Comment(String comment, Game game, String mail) {
		super();
		this.comment = comment;
		this.game = game;
		this.mail = mail;
	}



	public String getMail() {
		return mail;
	}


	public void setMail(String mail) {
		this.mail = mail;
	}


	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}
	
}
