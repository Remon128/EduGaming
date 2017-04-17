package swe2.springbootstarter.question;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionService {

	@Autowired
	private QuestionRepository questionRepository;
	
	public List<Question> getAllQuestions(String gameName){
		
		return questionRepository.findByGameName(gameName);
		
	}
	
	
	public void addQuestion(Question question){
		questionRepository.save(question);
	}
	

	public void updateQuestion(Question question) {
		questionRepository.save(question);	
	}

	public void deleteQuestion(String id) {
		questionRepository.delete(id);	
		
	}


}
