package swe2.springbootstarter.comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import swe2.springbootstarter.entities.Comment;

@Service
public class CommentService {
	
	@Autowired
	private CommentRepository commentRepo;
	
	public void addComment(Comment comment){
		commentRepo.save(comment);
	}
	
	public List<Comment> getComments(Integer id){
		return commentRepo.findByGame_Id(id);
	}
	
	
}
