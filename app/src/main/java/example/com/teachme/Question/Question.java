package example.com.teachme.Question;


import example.com.teachme.model.Game;

public abstract class Question {

	private String id;
	private String description;

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
