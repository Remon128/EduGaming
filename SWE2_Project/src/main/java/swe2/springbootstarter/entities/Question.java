package swe2.springbootstarter.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Inheritance
public abstract class Question {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String description;

	@ManyToOne
	@JoinColumn
	private Game game;

	public Question() {

	}

	public Question(Integer id, String description, Game game) {
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

}
