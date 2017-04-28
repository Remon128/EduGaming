package swe2.springbootstarter.question;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import swe2.springbootstarter.entities.MCQ;
import swe2.springbootstarter.entities.Question;

@Service
public class QuestionService {

	@Autowired
	private QuestionRepository questionRepository;
	
	public List<Question> getAllQuestions(Integer gameName){
		
		ArrayList<Question> questions = new ArrayList<>();
		questionRepository.findByGameId(gameName).forEach(questions::add);
		return questions;
		
	}
	
//	public Question getQuestion(Long id){
//		return questionRepository.findOne(id);
//	}
	
	public void addQuestion(Question question){
		questionRepository.save(question);
	}
	
	

	public void updateQuestion(MCQ question) {
		questionRepository.save(question);	
	}
//
//	public void deleteQuestion(Long id) {
//		questionRepository.delete(id);	
//		
//	}

	public boolean isQuestionExist(Question question) {
        return questionRepository.exists(question.getId());
	}

}
