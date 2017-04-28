package swe2.springbootstarter.entities;

import javax.persistence.Entity;

@Entity
public class MCQ extends Question {

	private String[] choices;
	private int answer;

	public String[] getChoices() {
		return choices;
	}

	public void setChoices(String[] choices) {
		this.choices = choices;
	}

	public int getAnswer() {
		return answer;
	}

	public void setAnswer(int answer) {
		this.answer = answer;
	}

	public MCQ() {
		super();
	}

	public MCQ(Integer id, String description, Game game) {
		super(id, description, game);
		// TODO Auto-generated constructor stub
	}

	public MCQ(String[] choices, int answer) {
		super();
		this.choices = choices;
		this.answer = answer;
	}

	

}
