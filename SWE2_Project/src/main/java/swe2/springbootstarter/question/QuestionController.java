package swe2.springbootstarter.question;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class QuestionController {

	@Autowired
	private QuestionService questionService;

	@RequestMapping(method = RequestMethod.GET, value = "/getQuestionsByGameName/{gameName}")
	public List<Question> getAllQuestions(@PathVariable String gameName) {
		return questionService.getAllQuestions(gameName);
	}
	///////////////////////////////////////////////////////////

	@RequestMapping(method = RequestMethod.POST, value = "/addQuestion")
	public void addQuestion(@RequestBody Question question) {
	
		questionService.addQuestion(question);
	}
	///////////////////////////////////////////////////////////
	@RequestMapping(method = RequestMethod.PUT, value = "/questionUpdate/{questionId}")
	public void updateQuestion(@RequestBody Question question, @PathVariable String questionId) {
		question.setId(questionId);
		questionService.updateQuestion(question);
	}
	///////////////////////////////////////////////////////////
	@RequestMapping(method = RequestMethod.DELETE, value = "questionDelete/{questionId}")
	public void deleteQuestion(@PathVariable String id) {
		questionService.deleteQuestion(id);
	}

}
