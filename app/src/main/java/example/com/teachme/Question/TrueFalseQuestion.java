package example.com.teachme.Question;

import example.com.teachme.Game.Game;

public class TrueFalseQuestion extends Question {

	private Boolean answer;

	public Boolean getAnswer() {
		return answer;
	}

	public void setAnswer(Boolean answer) {
		this.answer = answer;
	}

	public TrueFalseQuestion(String id, String description, Game game, Boolean answer) {
		super(id, description, game);
		this.answer = answer;
	}

	public TrueFalseQuestion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TrueFalseQuestion(String id, String description, Game game) {
		super(id, description, game);
		// TODO Auto-generated constructor stub
	}

}
