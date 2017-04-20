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
import swe2.util.CustomErrorType;

@RestController
@RequestMapping("/api")
public class GameController {
	
	public static final Logger logger = LoggerFactory.getLogger(GameController.class);
	
	@Autowired
	private GameService gameService;
	@Autowired
	private CourseService courseService;
	
	@RequestMapping(value = "/games/{courseName}", method = RequestMethod.GET)
    public ResponseEntity<List<Game>> listAllGames(@PathVariable String courseName) {
        List<Game> games = gameService.getAllGames(courseName);
        if (games.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Game>>(games, HttpStatus.OK);
    }
	///////////////////////////////////////////////
	@RequestMapping("/game/{name}")
	public ResponseEntity<?> getGame(@PathVariable String name){
		logger.info("getting game with name {}", name);
		Game game = gameService.getGame(name);
		
		if(game == null){
			logger.error("Game with name {} not found.", name);
			return new ResponseEntity<>(new CustomErrorType("Game with name " + name 
                    + " not found"), HttpStatus.NOT_FOUND);
		}
		 return new ResponseEntity<Game>(game, HttpStatus.OK);
	}
	////////////////////////////////////////////////
	 @RequestMapping(value = "/game/{courseName}", method = RequestMethod.POST)
	public ResponseEntity<?> createGame(@RequestBody Game game,UriComponentsBuilder ucBuilder, @PathVariable String courseName) {
	        logger.info("Creating Game : {}", game);
	 
	        if (gameService.isGameExist(game)) {
	            logger.error("Unable to create. A Game with name {} already exist", game.getName());
	            return new ResponseEntity<>(new CustomErrorType("Unable to create. A Game with name " + 
	            game.getName() + " already exist."),HttpStatus.CONFLICT);
	        }
	        
	        game.setCourse(courseService.getCourse(courseName));
	        gameService.addGame(game);
	 
	        HttpHeaders headers = new HttpHeaders();
	        headers.setLocation(ucBuilder.path("/api/game/{name}").buildAndExpand(game.getName()).toUri());
	        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	    }
	/////////////////////////////////////////////////
	 @RequestMapping(value = "/gameUpdate", method = RequestMethod.PUT)
	public ResponseEntity<?> updateGame(@RequestBody Game game) {
	        logger.info("Updating Game with name {}", game.getName());
	 
	 
	        gameService.updateGame(game);
	        return new ResponseEntity<Game>(game, HttpStatus.OK);
	    }
	///////////////////////////////////////////////////
	 @RequestMapping(value = "/game/{name}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteGame(@PathVariable("name") String name) {
	        logger.info("Fetching & Deleting Game with name {}", name);
	 
	        Game game = gameService.getGame(name);
	        if (game == null) {
	            logger.error("Unable to delete. Game with name {} not found.", name);
	            return new ResponseEntity<>(new CustomErrorType("Unable to delete. Game with name " + name + " not found."),
	                    HttpStatus.NOT_FOUND);
	        }
	        gameService.deleteGame(name);
	        return new ResponseEntity<Game>(HttpStatus.NO_CONTENT);
	    }
}
