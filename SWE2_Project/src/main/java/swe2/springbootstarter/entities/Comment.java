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
	
	public Game getGame() {
		return game;
	}



	public void setGame(Game game) {
		this.game = game;
	}

	@ManyToOne
	@JoinColumn
	private Users user;
	
	
	
	public Comment() {
		
	}

	

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Comment(String comment, Users user, Game game) {
		super();
		this.comment = comment;
		this.user = user;
		this.game = game;
	}



	public Users getUser() {
		return user;
	}



	public void setUser(Users user) {
		this.user = user;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}
