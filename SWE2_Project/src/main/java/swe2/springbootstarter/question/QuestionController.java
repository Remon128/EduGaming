package swe2.springbootstarter.question;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import swe2.springbootstarter.entities.Question;
import swe2.springbootstarter.game.GameService;
import swe2.util.CustomErrorType;

@RestController
@RequestMapping("/api")
public class QuestionController {

	public static final Logger logger = LoggerFactory.getLogger(QuestionController.class);

	@Autowired
	private QuestionService questionService;
	@Autowired
	private GameService gameService;

	@RequestMapping(method = RequestMethod.GET, value = "/getQuestionsByGameId/{gameId}")
	public ResponseEntity<List<Question>> getAllQuestions(@PathVariable Integer gameId) {
		List<Question> questions = questionService.getAllQuestions(gameId);
		if(questions.isEmpty()){
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        // You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Question>>(questions, HttpStatus.OK);
	}
	///////////////////////////////////////////////////////////

	@RequestMapping(method = RequestMethod.POST, value = "create/question/{gameId}")
	public ResponseEntity<?> addQuestion(@RequestBody Question question,@PathVariable Integer gameId,UriComponentsBuilder ucBuilder) {
		logger.info("Creating Question : {}", question);

		if (!gameService.isGameExist(gameId)) {
            logger.error("Unable to create. A Question with name {} already exist", question.getId());
            return new ResponseEntity<>(new CustomErrorType("Unable to create. A Question with name " +
            question.getId() + " already exist."),HttpStatus.CONFLICT);
        }
		question.setGame(gameService.getGame(gameId));
		questionService.addQuestion(question);

        return new ResponseEntity<Question>(question, HttpStatus.CREATED);
	}
	///////////////////////////////////////////////////////////
//	@RequestMapping(method = RequestMethod.PUT, value = "/questionUpdate/{questionId}")
//	public ResponseEntity<?> updateQuestion(@RequestBody Question question, @PathVariable String questionId) {
//		logger.info("Updating Question with id {}", question.getId());
//		question.setId(questionId);
//		questionService.updateQuestion(question);
//		return new ResponseEntity<Question>(question, HttpStatus.OK);
//	}
//	///////////////////////////////////////////////////////////
//	@RequestMapping(method = RequestMethod.DELETE, value = "questionDelete/{questionId}")
//	public ResponseEntity<?> deleteQuestion(@PathVariable String questionId) {
//		logger.info("Fetching & Deleting Question with id {}", questionId);
//		Question question = questionService.getQuestion(questionId);
//		if(question == null){
//			logger.error("Unable to delete. Question with id {} not found.", questionId);
//            return new ResponseEntity<>(new CustomErrorType("Unable to delete. Question with id " + questionId + " not found."),
//                    HttpStatus.NOT_FOUND);
//		}
//		questionService.deleteQuestion(questionId);
//		return new ResponseEntity<Question>(HttpStatus.NO_CONTENT);
//	}



}
