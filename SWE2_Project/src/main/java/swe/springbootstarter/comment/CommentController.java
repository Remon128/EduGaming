package swe.springbootstarter.comment;

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
import swe2.springbootstarter.entities.Comment;

@RestController
@RequestMapping("/api")
public class CommentController {
	
	public static final Logger logger = LoggerFactory.getLogger(CommentController.class);
	
	@Autowired
	private CommentService commentService;
	
	@RequestMapping(value = "/comment/getByGameId/{gameId}", method = RequestMethod.GET)
    public ResponseEntity<List<Comment>> getCoursesByTeacherMail(@PathVariable ("gameId") Integer gameId) {
        List<Comment> comments = commentService.getComments(gameId);
        if (comments.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
           
            
        }
        return new ResponseEntity<List<Comment>>(comments, HttpStatus.OK);
    }
	///////////////////////////////////////////////
	 @RequestMapping(value = "/comment/addComment", method = RequestMethod.POST)
	    public ResponseEntity<?> createCourse(@RequestBody Comment comment) {
	        logger.info("Creating Comment: {}", comment);
	    
	        commentService.addComment(comment);
	 
	        
	        return new ResponseEntity<Comment>(comment, HttpStatus.CREATED);
	    }
}
