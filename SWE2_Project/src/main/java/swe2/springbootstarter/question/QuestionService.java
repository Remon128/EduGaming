package swe2.springbootstarter.question;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {

	@Autowired
	private QuestionRepository questionRepository;
	
	public List<Question> getAllQuestions(String gameName){
		ArrayList<Question> questions = new ArrayList<>();
		questionRepository.findByGameName(gameName).forEach(questions::add);
		return questions;
	}
	
	public Question getQuestion(String id){
		return questionRepository.findOne(id);
	}
	
	public void addQuestion(Question question){
		questionRepository.save(question);
	}
	
	public void addQuestion(TrueFalseQuestion question){
		questionRepository.save(question);
	}

	public void updateQuestion(Question question) {
		questionRepository.save(question);	
	}

	public void deleteQuestion(String id) {
		questionRepository.delete(id);	
		
	}


}
