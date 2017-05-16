package swe2.springbootstarter.game;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import swe2.springbootstarter.course.CourseService;
import swe2.springbootstarter.entities.Game;
import swe2.util.CustomErrorType;

@RestController
@RequestMapping("/api")
public class GameController {

	public static final Logger logger = LoggerFactory.getLogger(GameController.class);

	@Autowired
	private GameService gameService;
	@Autowired
	private CourseService courseService;
	
	
	/*
	 * <<<<<<< HEAD
	 * 
	 * @RequestMapping(value = "/games/{courseName}", method =
	 * RequestMethod.GET) public ResponseEntity<?> listAllGames(@PathVariable
	 * String courseName) { List<Game> games =
	 * gameService.getAllGames(courseName);
	 */

	@RequestMapping(value = "/games/{courseId}", method = RequestMethod.GET)
	public ResponseEntity<?> listAllGames(@PathVariable Integer courseId) {
		List<Game> games = gameService.getAllGames(courseId);
		if (games.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Game>>(games, HttpStatus.OK);
	}

	////////////////////////////////////////////////
	@RequestMapping(value = "/game/{courseId}", method = RequestMethod.POST)
	public ResponseEntity<?> createGame(@RequestBody Game game, UriComponentsBuilder ucBuilder,
			@PathVariable Integer courseId) {
		logger.info("Creating Game : {}", game);

		if (!courseService.isCourseExist(courseId)) {
			logger.error("Unable to create. A Game with name {} already exist", game.getName());
			return new ResponseEntity<>(
					new CustomErrorType("Unable to create. A Game with name " + game.getName() + " already exist."),
					HttpStatus.CONFLICT);
		}

		game.setCourse(courseService.getCourse(courseId));
		gameService.addGame(game);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/game/{name}").buildAndExpand(game.getName()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/game/Copy/{courseId}", method = RequestMethod.POST)
	public ResponseEntity<?> copyGame(@RequestBody Game game,@PathVariable Integer courseId) {
		logger.info("Copying Game : {}", game);

		game.setId(null);
		
		game.setCourse(courseService.getCourse(courseId));
		gameService.addGame(game);

		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}
	
	/*
	 @RequestMapping(value = "/game/cancel", method = RequestMethod.PUT)
	 public ResponseEntity<?> cancelGame(@RequestBody Game game) {

		 logger.info("Canceling Game with name {}", game.getName());
	
	
		 gameService.cancelGame(game);
		 return new ResponseEntity<Game>(game, HttpStatus.OK);
	 }

	 @RequestMapping(value = "/game/uncancel", method = RequestMethod.PUT)
	 public ResponseEntity<?> unCancelGame(@RequestBody Game game) {

		 logger.info("unCanceling Game with name {}", game.getName());
	
	
		 gameService.uncancelGame(game);
		 return new ResponseEntity<Game>(game, HttpStatus.OK);
	 }
	 
	 @RequestMapping(value = "/game/addCollaborator", method = RequestMethod.PUT)
	 public ResponseEntity<?> addGameCollaborator(@RequestBody Game game) {

		 logger.info("unCanceling Game with name {}", game.getName());
	
	
		 gameService.uncancelGame(game);
		 return new ResponseEntity<Game>(game, HttpStatus.OK);
	 }
	 
	 */
	
	/////////////////////////////////////////////////
	// @RequestMapping(value = "/gameUpdate", method = RequestMethod.PUT)
	// public ResponseEntity<?> updateGame(@RequestBody Game game) {
	// logger.info("Updating Game with name {}", game.getName());
	//
	//
	// gameService.updateGame(game);
	// return new ResponseEntity<Game>(game, HttpStatus.OK);
	// }
	// ///////////////////////////////////////////////////
	// @RequestMapping(value = "/game/{name}", method = RequestMethod.DELETE)
	// public ResponseEntity<?> deleteGame(@PathVariable("name") String name) {
	// logger.info("Fetching & Deleting Game with name {}", name);
	//
	// Game game = gameService.getGame(name);
	// if (game == null) {
	// logger.error("Unable to delete. Game with name {} not found.", name);
	// return new ResponseEntity<>(new CustomErrorType("Unable to delete. Game
	// with name " + name + " not found."),
	// HttpStatus.NOT_FOUND);
	// }
	// gameService.deleteGame(name);
	// return new ResponseEntity<Game>(HttpStatus.NO_CONTENT);
	// }

	///////////////////////////////////////////////
	// @RequestMapping(value="/game/{name}", method = RequestMethod.GET)
	// public ResponseEntity<?> getGame(@PathVariable String name){
	// logger.info("getting game with name {}", name);
	// Game game = gameService.getGame(name);
	//
	// if(game == null){
	// logger.error("Game with name {} not found.", name);
	// return new ResponseEntity<>(new CustomErrorType("Game with name " + name
	// + " not found"), HttpStatus.NOT_FOUND);
	// }
	// return new ResponseEntity<Game>(game, HttpStatus.OK);
	// }
}
