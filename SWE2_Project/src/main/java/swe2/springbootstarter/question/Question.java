package swe2.springbootstarter.question;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import swe2.springbootstarter.game.Game;

@Entity
@Inheritance
public abstract class Question {

	@Id
	private String id;
	private String description;

	@ManyToOne
	@JoinColumn
	private Game game;

	public Question() {

	}

	public Question(String id, String description, Game game) {
		super();
		this.id = id;
		this.description = description;
		this.game = game;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

}
