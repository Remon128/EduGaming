package swe2.springbootstarter.comment;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import swe2.springbootstarter.entities.Comment;

@Service
public class CommentService {
	
	@Autowired
	private CommentRepository commentRepository;
	
	public void addComment(Comment comment){
		
		commentRepository.save(comment);
	}
	
	public List<Comment> getComments(Integer id){
		ArrayList<Comment> comments = new ArrayList<>();
		commentRepository.findByGameId(id).forEach(comments::add);
		return comments;
	}
	
	
}
