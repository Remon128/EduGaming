package swe2.springbootstarter.question;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuestionController {

	@Autowired
	private QuestionService questionService;

	@RequestMapping("/topics/{topicId}/courses/{courseId}/games/{gameId}/questions")
	public List<Question> getAllQuestions(@PathVariable String gameId) {
		return questionService.getAllQuestions(gameId);
	}

	@RequestMapping("/question/{id}")
	public Question getQuestion(@PathVariable String id) {
		return questionService.getQuestion(id);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/question")
	public void addQuestion(@RequestBody Question question) {
	
		questionService.addQuestion(question);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/topics/{topicId}/courses/{courseId}/games/{gameId}/questions/{id}")
	public void updateQuestion(@RequestBody Question question, @PathVariable String gameId, @PathVariable String id) {
		questionService.updateQuestion(question);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/topics/{topicId}/courses/{courseId}/games/{gameId}/questions/{id}")
	public void deleteQuestion(@PathVariable String id) {
		questionService.deleteQuestion(id);
	}

}
